package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.util.List;

/**
 * 账户的持久层实现类
 */
public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;

    //修改开始
    //创建一个存入了一个连接的工具类
    private ConnectionUtil connectionUtil;

    //set方式注入实例化对象connectionUtil，用于获取工具类ConnectionUtil中保存的连接
    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }
    //修改结束

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    /**
     * 更新账户的操作
     *
     * @param account 需要修改的账户名
     */
    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(
                    //修改内容开始
                    //注意：每个操作数据库的语句都要添加这部分修改内容以使用工具类ConnectionUtil中保存的连接
                    connectionUtil.getTheadConnection(),
                    //修改内容结束
                    "update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据名称查找账户名的操作
     *
     * @param accountName 需要查找的账户名
     * @return 查找到的账户名
     */
    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtil.getTheadConnection(), "select * from account where name=?", new BeanListHandler<Account>(Account.class), accountName);
            if (accounts == null || accounts.size() == 0) {
                return null;
            }
            if (accounts.size() > 1) {
                throw new RuntimeException("结果集不唯一，数据有问题");
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
