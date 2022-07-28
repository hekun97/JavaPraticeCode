package com.itheima.test;

import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import com.itheima.service.impl.AccountServiceImplPrimitive;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {
    //1.获取Spring核心容器
    ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    //2.根据id获取bean对象，这里获取的是被工厂类BeanFactory增强过的业务层对象proxyAccountService
    //注意这里的pas的类型只能是接口IAccountService，因为增强过后的对象pas和AccountServiceImpl和proxyAccountServicePrimitive的对象不一样，是个新的对象
    IAccountService pas = (IAccountService) ac.getBean("proxyAccountService");
    @Test
    public void tsetTransfer(){
        pas.transfer("张三","李四",100f);
    }
}