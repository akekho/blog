package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.Account;

import java.security.NoSuchAlgorithmException;

/**
 * 账户服务
 * Created by Jiateng on 7/15/18.
 */
public interface AccountService {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return 账户信息，登录失败返回null
     */
    Account login(String username, String password) throws ServiceException, NoSuchAlgorithmException;

    /**
     * 注册
     *
     * @param account
     * @return 注册成功返回账户信息，失败返回null
     */
    Account signUp(Account account) throws ServiceException, NoSuchAlgorithmException;


    /**
     * 检查token
     *
     * @param token
     * @return 成功返回用户名，失败返回Null
     */
    String verifyToken(String token);
}
