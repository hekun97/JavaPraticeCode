package com.itheima.service.impl;

import com.itheima.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 模拟转账业务的实现类
 */
//将该类交给Spring IOC管理
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    /**
     * 模拟保存账户
     */
    @Override
    public void saveAccount() {
        System.out.println("业务层保存了账户");
        //人为添加异常，使程序运行异常通知
        //int i = 1/0;
    }


    /**
     * 模拟更新账户
     * @param i
     */
    @Override
    public void updateAccount(int i) {
        System.out.println("业务层更新了账户");
    }

    /**
     * 模拟删除账户
     * @return
     */
    @Override
    public int deleteAccount() {
        System.out.println("业务层删除了账户");
        return 1;
    }
}