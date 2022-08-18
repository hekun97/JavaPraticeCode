package com.itheima.jdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //2.1准备数据源：spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test2");
        ds.setUsername("root");
        ds.setPassword("root1234");

        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //2.给jt设置数据源
        jt.setDataSource(ds);
        //3.执行操作
        jt.execute("insert into account(name,money)values('ddd',7000)");
    }
}
