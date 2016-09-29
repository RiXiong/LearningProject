package org.zrx.springframework.aop;

/**
 * Function:    BaseAspect
 * Author:      zhangrixiong
 * DateTime:    2016/9/26 10:01
 *
 * 参考文章：http://www.eclipse.org/aspectj/doc/next/progguide/semantics-advice.html （eclipse 官网）
 * 关键术语：
 *        aspect： 切面
 *        Advice （通知） before after around
 *        pointcut （切点）
 *
 */
public aspect BaseAspect {

    // call(* org.zrx.springframework.samples.mvc..*.*(..));  表示调用的地方，非AOP方法中
    // execution(* org.zrx.springframework.samples.mvc..*.*(..)); 表示执行的地方，aop方法中
    // execution(* main(..)) && !within(HelloAspectDemo);  !within(HelloAspectDemo) ---- 表示不拦截该类
    // execution(* main(..)) && !within(HelloAspectDemo);  !withincode(HelloAspectMethod) ---- 表示不拦截该类方法
    // thisJoinPoint.getSourceLocation()　 查看代码的位置。
    pointcut HelloWorldPointCut() : execution(* org.zrx.springframework.samples.mvc..*.*(..));
    pointcut HelloServicePointCut() : execution(* org.zrx.springframework.samples.service..*.*(..));

    before() : HelloWorldPointCut() {
        System.out.println( "Hello world  对接口进行访问统计和日志输出 =====" + thisJoinPoint.getSourceLocation());
    }

//    after() returning (Object o): HelloWorldPointCut() {
//        System.out.println("Returned normally with " + o.toString());
//    }
//    after() throwing (Exception e): HelloWorldPointCut() {
//        System.out.println("Threw an exception: 执行方法后，抛出异常通知！ " + e);
//    }
    after(): HelloWorldPointCut(){
        System.out.println(  "Returned or threw an Exception  返回值或 抛出异常！======" + thisJoinPoint.getSourceLocation());
        System.out.println( thisJoinPoint.getSignature() );
    }

    Object around() : HelloServicePointCut(){
        System.out.println("Entering : ");
        Object ob =  proceed();
        System.out.println( " around()  ！============" + thisJoinPoint.getSourceLocation());
        return ob;
    }

}
