package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类
 */
//1.继承父类JdbcDaoSupport以使用其中的jdbcTemplate
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    //2.去除注入jdbcTemplate的重复代码
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 根据Id查询账户
     * @param accountId
     * @return
     */
    @Override
    public Account findAccountById(Integer accountId) {
        //3.操作数据库的代码全部改为使用父类的jdbcTemplate对象
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    /**
     * 根据名称查询账户
     * @param accountName
     * @return
     */
    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    /**
     * 更新账户
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
            jdbcTemplate.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }
}