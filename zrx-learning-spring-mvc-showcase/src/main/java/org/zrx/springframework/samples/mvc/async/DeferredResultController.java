package org.zrx.springframework.samples.mvc.async;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;


/**
 * Function:    DeferredResultController 延期结果控制器
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:19
 * <p>
 *     延迟结果控制器，
 *     Spring在schedule这块支持JDK Timer、concurrent、quartz三种，
 *     这三种schedule都是基于scheduler->trigger->job的基本流程，
 *     因此spring通过TimerFactoryBean、ScheduledExecutorFactoryBean和SchedulerFactoryBean分别实现JDK Timer、concurrent和quartz的基本流程。
 *
 *     顺着scheduler->trigger->job的思路，Spring又分别对JDK Timer、concurrent、quartz的trigger进行了封装，
 *     暴露出时间调度的配置参数，三种封装类分别为ScheduledTimerTask、ScheduledExecutorTask和CronTriggerBean+SimpleTriggerBean。
 *     三种trigger封装类分别根据实现机制的特点暴露出时间调度配置并串联起scheduler和具体job任务
 * </p>
 */
@Controller
@RequestMapping("/async")
public class DeferredResultController {

    // responseBodyQueue 请求队列
    private final Queue<DeferredResult<String>> responseBodyQueue = new ConcurrentLinkedQueue<DeferredResult<String>>();

    // model And View 视图队列
    private final Queue<DeferredResult<ModelAndView>> mavQueue = new ConcurrentLinkedQueue<DeferredResult<ModelAndView>>();

    // exceptionQueue 异常队列
    private final Queue<DeferredResult<String>> exceptionQueue = new ConcurrentLinkedQueue<DeferredResult<String>>();

    @RequestMapping("/deferred-result/response-body")
    public @ResponseBody DeferredResult<String> deferredResult() {
        DeferredResult<String> result = new DeferredResult<String>();
        this.responseBodyQueue.add(result);
        return result;
    }

    @RequestMapping("/deferred-result/model-and-view")
    public DeferredResult<ModelAndView> deferredResultWithView() {
        DeferredResult<ModelAndView> result = new DeferredResult<ModelAndView>();
        this.mavQueue.add(result);
        return result;
    }

    @RequestMapping("/deferred-result/exception")
    public @ResponseBody DeferredResult<String> deferredResultWithException() {
        DeferredResult<String> result = new DeferredResult<String>();
        this.exceptionQueue.add(result);
        return result;
    }

    @RequestMapping("/deferred-result/timeout-value")
    public @ResponseBody DeferredResult<String> deferredResultWithTimeoutValue() {

        // Provide a default result in case of timeout and override the timeout value
        // set in src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml

        return new DeferredResult<String>(1000L, "Deferred result after timeout");
    }

    /**
     * 业务层的切入点
     */
    @Inject
    org.zrx.springframework.samples.service.HelloService helloService;

    @Scheduled(fixedRate=2000)
    public void processQueues() {
        helloService.hello();
        for (DeferredResult<String> result : this.responseBodyQueue) {
            result.setResult("Deferred result");
            this.responseBodyQueue.remove(result);
        }
        for (DeferredResult<String> result : this.exceptionQueue) {
            result.setErrorResult(new IllegalStateException("DeferredResult error"));
            this.exceptionQueue.remove(result);
        }
        for (DeferredResult<ModelAndView> result : this.mavQueue) {
            result.setResult(new ModelAndView("views/html", "javaBean", new JavaBean("bar", "apple")));
            this.mavQueue.remove(result);
        }
    }

    @ExceptionHandler
    @ResponseBody
    public String handleException(IllegalStateException ex) {
        return "Handled exception: " + ex.getMessage();
    }
}
