package org.zrx.springframework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import java.text.SimpleDateFormat;


/**
 * Function:    LogAspect
 * Author:      zhangrixiong
 * DateTime:    2016/9/23 18:04
 */
@Aspect
public class LogAspect {

    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间

    @Before("execution(* org.zrx.springframework.samples.mvc..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        System.out.println("================================Hello World!  start!");
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

//    @After("execution(* org.zrx.springframework.samples.mvc..*.*(..))")
//    public void doAfterInServiceLayer(JoinPoint joinPoint) {
//        System.out.println("================================Hello World!  end!");
//        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
//        this.printOptLog();
//    }

//    @Around("execution(* org.zrx.springframework.samples.mvc..*.*(..))")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        String result = "Hello World!";
//        System.out.println("================================Hello World! before ");
//        pjp.proceed();
//        System.out.println("================================Hello World! After");
//        return result;
//    }

    private void printOptLog() {
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
    }
}
