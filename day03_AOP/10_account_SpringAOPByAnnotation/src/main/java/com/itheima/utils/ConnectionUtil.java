package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
@Component("connectionUtil")
public class ConnectionUtil {
    //创建一个存数据库连接的本地线程
    private ThreadLocal<Connection> t1 = new ThreadLocal<>();
    //创建一个获取数据库连接的数据源
    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getTheadConnection() {
        try {
            //1.先从TheadLocal上获取连接
            Connection conn = t1.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.没有就从数据源中获取一个连接，并把连接存入到ThreadLocal中
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            //4.返回当前线程的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 在事务操作完成后，释放当前线程上的连接，完成解绑，避免下个事务获取的连接还是旧的
     */
    public void removeConnection(){
        t1.remove();
    }
}
