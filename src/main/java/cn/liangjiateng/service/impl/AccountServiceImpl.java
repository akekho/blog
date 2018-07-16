package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.AccountMapper;
import cn.liangjiateng.pojo.DO.Account;
import cn.liangjiateng.service.AccountService;
import cn.liangjiateng.util.EncryUtil;
import cn.liangjiateng.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by Jiateng on 7/15/18.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account login(String username, String password) throws ServiceException, NoSuchAlgorithmException {
        if (username == null || password == null) {
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "用户名或密码不能为空");
        }
        Account account = accountMapper.getAccountByUsernameAndPassword(username, EncryUtil.getMd5(password));
        if (account == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "用户名或者密码不正确");
        String token = TokenUtil.createToken(account.getUsername(), account.getPassword(), TokenUtil.ONE_DAY);
        account.setToken(token);
        accountMapper.updateAccountTokenById(account.getId(), token);
        return account;
    }

    @Override
    public Account signUp(Account account) throws ServiceException, NoSuchAlgorithmException {
        if (account == null || account.getUsername() == null || account.getPassword() == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "用户名或密码不能为空");
        Account check = accountMapper.getAccountByUsername(account.getUsername());
        if (check != null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "该用户名已经存在");
        if (account.getRealName() == null)
            account.setRealName(account.getUsername());
        account.setPassword(EncryUtil.getMd5(account.getPassword()));
        String token = TokenUtil.createToken(account.getUsername(), account.getPassword(), TokenUtil.ONE_DAY);
        account.setToken(token);
        account.setId(accountMapper.insertAccount(account));
        return account;
    }

    @Override
    public String verifyToken(String token) {
        if (token == null || TokenUtil.isExpire(token))
            return null;
        Map<String, Object> map = TokenUtil.decodeToken(token);
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if (accountMapper.getAccountByUsernameAndPassword(username, password) == null)
            return null;
        return username;
    }
}
