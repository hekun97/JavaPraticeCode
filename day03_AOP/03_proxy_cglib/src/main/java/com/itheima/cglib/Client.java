package com.itheima.cglib;

import com.itheima.proxy.IProducer;
import com.itheima.proxy.Producer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 消费者
 */
public class Client {
    public static void main(String[] args) {
        //1。实例化Producer对象producer
        //添加final修饰，避免值被随意更改
        final Producer producer = new Producer();
        //删除之前基于接口的动态代理所有代码
        //2.基于子类的动态代理，第一个参数是固定写法
        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param o 等同之前的proxy
             * @param method
             * @param objects 等同之前的args
             *                以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
             * @param methodProxy 当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //提供增强的代码
                Object returnValue=null;
                //1.获取方法执行的参数
                Float money = (Float) objects[0];
                //2.判断当前方法是不是销售方法
                if("saleProducer".equals(method.getName())){
                    //3.如果是销售方法就对该方法进行增强
                    returnValue = method.invoke(producer,money*0.8f);
                }
                //4.返回销售结果
                return returnValue;
            }
        });
        cglibProducer.saleProducer(12000f);
    }
}
