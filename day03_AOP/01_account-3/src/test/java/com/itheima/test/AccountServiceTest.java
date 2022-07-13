package com.itheima.test;

import com.itheima.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {
    //1.获取Spring核心容器
    ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    //2.根据id获取bean对象，这里获取的是业务层对象as
    AccountServiceImpl as = (AccountServiceImpl) ac.getBean("accountService");
    @Test
    public void tsetTransfer(){
        as.transfer("张三","李四",100f);
    }

}