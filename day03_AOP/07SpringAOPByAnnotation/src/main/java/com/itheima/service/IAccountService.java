package com.itheima.service;

/**
 * 模拟转账业务的接口。
 *  这里的三个方法，分别是
 *      无返回值无参类型；
 *      无返回值有参类型；
 *      有返回值无参类型。
 * 用于模拟实际业务中能遇到的大部分情况。
 */
public interface IAccountService {

    /**
     * 模拟保存账户
     */
    void saveAccount();


    /**
     * 模拟更新账户
     * @param i
     */
    void updateAccount(int i);

    /**
     * 模拟删除账户
     * @return
     */
    int deleteAccount();
}
