package com.itheima.proxy;

/**
 * 生产厂家
 */
public class Producer implements IProducer{
    /**
     * 销售产品
     *
     * @param money
     * @return
     */
    public void saleProducer(float money) {
        System.out.println("销售产品，并拿到钱：" + money);
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(float money) {
        System.out.println("提供售后服务，并拿到钱" + money);
    }
}
