package org.zrx.springframework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

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
        System.out.println("================================Hello World! ");
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

//    @After("execution(* org.zrx.springframework.samples.mvc..*.*(..))")
//    public void doAfterInServiceLayer(JoinPoint joinPoint) {
//        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
//        this.printOptLog();
//    }
//
//    @Around("execution(* org.zrx.springframework.samples.mvc..*.*(..))")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        String result = "Hello World!";
//        return result;
//    }

    private void printOptLog() {
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
    }
}
