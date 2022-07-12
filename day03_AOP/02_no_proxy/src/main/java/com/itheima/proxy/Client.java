package com.itheima.proxy;

/**
 * 消费者
 */
public class Client {
    public static void main(String[] args) {

        Producer producer = new Producer();
        producer.saleProducer(10000f);
    }
}
