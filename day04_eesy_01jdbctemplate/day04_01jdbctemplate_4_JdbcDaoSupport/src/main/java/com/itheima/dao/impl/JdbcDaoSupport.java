package com.itheima.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 此类用于抽取dao中的重复代码
 */
public class JdbcDaoSupport {
/*
    //1.定义jdbcTemplate
    private JdbcTemplate jdbcTemplate;

    //2.set方法用于设置jdbcTemplate
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //3.get方法用于在dao层中使用jdbcTemplate
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //4.不存在jdbcTemplate对象时，获取一个jdbcTemplate对象。我们再使用时通过调用该方法即可获取一个jdbcTemplate对象。
    public void setDataSource(DataSource dataSource) {
        if (jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }

    //5.用于创建jdbcTemplate对象，通过数据源dataSource去创建一个jdbcTemplate对象。
    private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/
}