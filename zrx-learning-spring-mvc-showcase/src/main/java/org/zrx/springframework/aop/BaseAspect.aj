package org.zrx.springframework.aop;

/**
 * Function:    BaseAspect
 * Author:      zhangrixiong
 * DateTime:    2016/9/26 10:01
 */
public aspect BaseAspect {

    pointcut HelloWorldPointCut() : execution(* org.zrx.springframework.samples.mvc..*.*(..));

    before() : HelloWorldPointCut(){
        System.out.println("Hello world fuck uu uuuu u u ");
    }

    after() returning (Object o): HelloWorldPointCut() {
        System.out.println("Returned normally with " + o);
    }
    after() throwing (Exception e): HelloWorldPointCut() {
        System.out.println("Threw an exception: " + e);
    }
    after(): HelloWorldPointCut(){
        System.out.println("Returned or threw an Exception");
    }
}
