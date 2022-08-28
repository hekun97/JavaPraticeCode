package com.itheima.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
@Component("transactionManagerUtil")
@Aspect
public class TransactionManagerUtil {
    //从ConnectionUtil工具类中获取数据库连接
    @Autowired
    private ConnectionUtil connectionUtil;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){};
    /**
     * 开启事务
     */
    @Before("pt1()")
    public void beginTransaction(){
        try {
            connectionUtil.getTheadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    @AfterReturning("pt1()")
    public void commitTransaction(){
        try {
            connectionUtil.getTheadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    @AfterThrowing("pt1()")
    public void rollbackTransaction(){
        try {
            connectionUtil.getTheadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放连接
     */
    @After("pt1()")
    public void release(){
        try {
            //1.把连接还回连接池中
            connectionUtil.getTheadConnection().close();
            //2.调用工具类ConnectionUtil中的removeConnection()方法，将连接和本地线程解绑
            connectionUtil.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
