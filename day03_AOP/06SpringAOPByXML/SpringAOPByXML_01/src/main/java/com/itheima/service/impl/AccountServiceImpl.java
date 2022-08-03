package com.itheima.service.impl;

import com.itheima.service.IAccountService;

/**
 * 模拟转账业务的实现类
 */
public class AccountServiceImpl implements IAccountService {
    /**
     * 模拟保存账户
     */
    @Override
    public void saveAccount() {
        System.out.println("业务层保存了账户");
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