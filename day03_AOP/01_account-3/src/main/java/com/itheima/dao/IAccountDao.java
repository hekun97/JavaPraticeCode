package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {


    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);


    /**
     * 根据账户名称查找账户
     * @return
     */
    Account findAccountByName(String accountName);
}
