package org.zrx.springframework.samples.mvc.async;


import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.context.request.async.CallableProcessingInterceptorAdapter;

/**
 * Function:    TimeoutCallableProcessingInterceptor 时间超时设置
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:19
 */
public class TimeoutCallableProcessingInterceptor extends CallableProcessingInterceptorAdapter {

    /**
     * Abstract adapter class for the {@link CallableProcessingInterceptor} interface,
     * 一个抽象的 适配器 类 ， 适配 {@link CallableProcessingInterceptor} interface 接口
     * @param request
     * @param task
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        throw new IllegalStateException("[" + task.getClass().getName() + "] timed out");
    }

}

