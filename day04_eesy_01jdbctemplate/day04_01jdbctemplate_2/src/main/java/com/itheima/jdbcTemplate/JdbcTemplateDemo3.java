package com.itheima.jdbcTemplate;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcTemplate的CRUD操作
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        //3.执行操作
        //3.1保存
//        jt.update("insert into account(name,money)values(?,?)","eee",3333f);
        //3.2更新
//        jt.update("update account set name=?,money=? where id=?","test",4567,7);
        //3.3删除
//        jt.update("delete from account where id=?",8);
        //3.4查询所有

        /*
        //方式一：通过自定义的 AccountRowMapper 类去手动把结果集中的数据封装到Account中，然后由spring把每个Account加到集合中
        List<Account> accounts = jt.query("select * from account where money > ?",new AccountRowMapper(),1000f);
        //方式二：通过Spring自带的BeanPropertyRowMapper去反射获取Account对象，并将数据封装到对象内，最后加入到集合中。
        List<Account> accounts = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1000f);
        for(Account account : accounts){
            System.out.println(account);
        }*/
        //3.5查询一个
//        List<Account> accounts = jt.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),1);
//        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));

        //3.6查询返回一行一列（使用聚合函数，但不加group by子句）
        Long count = jt.queryForObject("select count(*) from account where money > ?",Long.class,1000f);
        System.out.println(count);


    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 把结果集中的数据封装到Account中，然后由spring把每个Account加到集合中
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}