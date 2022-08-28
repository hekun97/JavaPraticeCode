package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
@Component("transactionManagerUtil")
@Aspect
public class TransactionManagerUtilByAround {
    //从ConnectionUtil工具类中获取数据库连接
    @Autowired
    private ConnectionUtil connectionUtil;

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1() {
    }

    ;

    /**
     * 开启事务
     */
    public void beginTransaction() {
        try {
            connectionUtil.getTheadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commitTransaction() {
        try {
            connectionUtil.getTheadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollbackTransaction() {
        try {
            connectionUtil.getTheadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        try {
            //1.把连接还回连接池中
            connectionUtil.getTheadConnection().close();
            //2.调用工具类ConnectionUtil中的removeConnection()方法，将连接和本地线程解绑
            connectionUtil.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            //1. 获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue=pjp.proceed(args);
            //4.提交事务
            this.commitTransaction();
            //返回结果
            return rtValue;
        }
        catch (Throwable t){
            //回滚事务
            throw new RuntimeException(t);
        }
        finally {
            //释放连接
            this.release();
        }
    }
}
