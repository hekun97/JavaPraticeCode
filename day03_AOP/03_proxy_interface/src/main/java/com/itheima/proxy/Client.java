package com.itheima.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 消费者
 */
public class Client {
    public static void main(String[] args) {
        //1。实例化Producer对象producer
        Producer producer = new Producer();
        //2.基于接口IProducer的动态代理，第一个和第二个参数都是固定写法
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 作用：执行被代理对象的任何接口的方法都会经过该方法，从而在内部进行加强处理。这里就有saleProducer()和afterService()方法。
             * @param proxy 代理对象的引用 固定写法
             * @param method 当前执行的方法 固定写法
             * @param args 当前执行方法所需的参数 固定写法
             * @return 和被代理对象方法有相同的返回值
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //提供增强的代码
                Object returnValue=null;
                //1.获取方法执行的参数
                Float money = (Float) args[0];
                //2.判断当前方法是不是销售方法
                if("saleProducer".equals(method.getName())){
                    //3.如果是销售方法就对该方法进行增强
                    returnValue = method.invoke(producer,money*0.8f);
                }
                //4.返回销售结果
                return returnValue;
            }
        });
        proxyProducer.saleProducer(10000f);
    }
}
