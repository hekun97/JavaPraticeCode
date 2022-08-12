package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
public class Logger {

    /**
     * 前置通知
     */
    public void beforePrintLog() {
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }

    /**
     * 后置通知
     */
    public void afterReturningPrintLog() {
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }

    /**
     * 最终通知
     */
    public void afterPrintLog() {
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    /**
     * 环绕通知 1
     */
    public void aroundPrintLog1() {
        System.out.println("环绕通知Logger类中的aroundPrintLog1方法开始记录日志了。。。");
    }

    /**
     * 环绕通知 2
     */
    public Object aroundPrintLog2(ProceedingJoinPoint pjp) throws Throwable {
        Object rtValue = null;
        try {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了。。。  前置");
            //获取参数
            Object[] args = pjp.getArgs();
            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法：logAdvice）
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了。。。  后置");
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了。。。  异常");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志了。。。  最终");
        }
    }
}
