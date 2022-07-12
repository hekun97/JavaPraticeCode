package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManagerUtil;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService{

    //持久层dao的实例化对象accountDao，用于操作数据库
    private IAccountDao accountDao;
    //这里的setAccountDao方法用于set方法注入持久层 dao
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
    //工具类TransactionManagerUtil的实例化对象txManagerUtil，用于统一管理事务
    private TransactionManagerUtil txManagerUtil;
    //set方式注入实例化对象txManagerUtil
    public void setTxManagerUtil(TransactionManagerUtil txManagerUtil) {
        this.txManagerUtil = txManagerUtil;
    }

    /**
     * 根据账户名称更新数据
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        try{
            //1.开启事务
            txManagerUtil.beginTransaction();
            //2.操作数据库
            accountDao.updateAccount(account);
            //3.提交事务
            txManagerUtil.commitTransaction();
        }catch (Exception e){
            //4.回滚事务
            txManagerUtil.rollbackTransaction();
            e.printStackTrace();
        }finally {
            //5.释放连接
            txManagerUtil.release();
        }
    }


    /**
     * 转账业务操作
     * @param sourceName   转出账户名称
     * @param targetName   转入账户名称
     * @param money        转账金额
     */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try{
            //1.开启事务
            txManagerUtil.beginTransaction();
            //2.操作数据库
            //2.1根据名称查询数据库中是否存在该转出账户；
            Account source = accountDao.findAccountByName(sourceName);
            //2.2根据名称查询数据库中是否存在该转入账户；
            Account target = accountDao.findAccountByName(targetName);
            //2.3转出账户减少钱；
            source.setMoney(source.getMoney()-money);//这里调用的是account实体类的bean方法
            //2.4转入账户增加钱;
            target.setMoney(target.getMoney()+money);
            //2.5更新转出账户；
            accountDao.updateAccount(source);//这里调用之前写过的更新数据库账户的方法
            //更新数据库时出现异常会报错，但因为1-6步使用的一个数据库连接，和统一的事务管理，因此可以正常回滚。
            int i = 1/0;
            //2.6更新转入账户。
            accountDao.updateAccount(target);
            //3.提交事务
            txManagerUtil.commitTransaction(); 
        }catch (Exception e){
            //4.回滚事务
            txManagerUtil.rollbackTransaction();
            e.printStackTrace();
        }finally {
            //5.释放连接
            txManagerUtil.release();
        }
    }
}
