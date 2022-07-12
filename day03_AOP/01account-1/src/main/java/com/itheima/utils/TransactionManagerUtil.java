package com.itheima.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
public class TransactionManagerUtil {
    //从ConnectionUtil工具类中获取数据库连接
    private ConnectionUtil connectionUtil;
    //使用set注入实例化对象connectionUtil，用于从工具类ConnectionUtil中获取保存的数据库连接
    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * 开启事务
     */
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
