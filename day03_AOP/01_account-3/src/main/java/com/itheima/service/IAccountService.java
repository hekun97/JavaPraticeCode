package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);


    /**
     * 转账
     * @param sourceName   转出账户名称
     * @param targetName   转入账户名称
     * @param money        转账金额
     */
    void transfer(String sourceName , String targetName , Float money);

}
