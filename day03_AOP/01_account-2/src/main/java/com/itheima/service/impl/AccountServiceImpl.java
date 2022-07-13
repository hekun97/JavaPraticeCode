package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService{

    private IAccountDao accountDao;
    //这里的setAccountDao方法用于set方法注入持久层 dao
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        //1.根据名称查询数据库中是否存在该转出账户；
        Account source = accountDao.findAccountByName(sourceName);
        //2.根据名称查询数据库中是否存在该转入账户；
        Account target = accountDao.findAccountByName(targetName);
        //3.转出账户减少钱；
        source.setMoney(source.getMoney()-money);//这里调用的是account实体类的bean方法
        //4.转入账户增加钱;
        target.setMoney(target.getMoney()+money);
        //5.更新转出账户；
        accountDao.updateAccount(source);//这里调用之前写过的更新数据库账户的方法
        int i = 1/0;
        //6.更新转入账户。
        accountDao.updateAccount(target);
    }
}
