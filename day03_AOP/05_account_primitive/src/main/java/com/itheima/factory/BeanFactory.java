package com.itheima.factory;

import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManagerUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    //用来获取未被增强的accountService对象
    private IAccountService accountService;

    //使用set方式注入setAccountService对象
    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    //工具类TransactionManagerUtil的实例化对象txManagerUtil，用于统一管理事务
    private TransactionManagerUtil txManagerUtil;

    //set方式注入实例化对象txManagerUtil
    public void setTxManagerUtil(TransactionManagerUtil txManagerUtil) {
        this.txManagerUtil = txManagerUtil;
    }

    /**
     * 用于获取Service代理对象，增强accountService对象的方法
     *
     * @return 返回被增强的Service代理对象 proxyAccountService
     */
    public IAccountService getAccountService() {
        IAccountService proxyAccountService =
                (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                        accountService.getClass().getInterfaces(), new InvocationHandler() {
                            //增强accountService类中方法的代码
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                Object rtValue = null;
                                try {
                                    //1.开启事务
                                    txManagerUtil.beginTransaction();
                                    //2.执行业务层的同名方法
                                    rtValue = method.invoke(accountService, args);
                                    //3.提交事务
                                    txManagerUtil.commitTransaction();
                                } catch (Exception e) {
                                    //4.回滚事务
                                    txManagerUtil.rollbackTransaction();
                                    e.printStackTrace();
                                } finally {
                                    //5.释放连接
                                    txManagerUtil.release();
                                }
                                return rtValue;
                            }
                        });
        //返回已经被代理过的service对象
        return proxyAccountService;
    }
}
