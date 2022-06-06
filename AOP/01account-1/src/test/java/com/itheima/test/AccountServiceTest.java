package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {
    //1.获取Spring核心容器
    ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
    //2.根据id获取bean对象，这里获取的是业务层对象as
    AccountServiceImpl as = (AccountServiceImpl) ac.getBean("accountService");

    /**
     * 查找所有账户
     */
    @Test
    public void findAll() {
        //3.通过业务层对象执行查找所有账户的方法findAllAccount()
        List<Account> accounts = as.findAllAccount();
        //4.遍历accounts集合输出每个账户
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 根据id查找单个账户
     */
    @Test
    public void testFindOne() {
        //3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    /**
     * 保存一个账户
     */
    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(12345f);
        //3.执行方法
        as.saveAccount(account);
    }

    /**
     * 更新指定id的账户信息
     */
    @Test
    public void testUpdate() {
        //3.执行方法
        Account account = as.findAccountById(4);
        account.setMoney(23456f);
        as.updateAccount(account);
    }

    /**
     * 删除指定id的账户
     */
    @Test
    public void testDelete() {
        //3.执行方法
        as.deleteAccount(4);
    }
}
