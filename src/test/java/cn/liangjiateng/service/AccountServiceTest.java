package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.AccountMapper;
import cn.liangjiateng.pojo.DO.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * Created by Jiateng on 7/15/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    @Before
    public void setUp() throws Exception {
        Account account = new Account();
        account.setUsername("liangjiateng");
        account.setPassword("123456");
        accountService.signUp(account);
    }

    @After
    public void tearDown() throws Exception {
        accountMapper.deleteAccountByUsername("liangjiateng");
        accountMapper.deleteAccountByUsername("liangjiateng1");
    }

    @Test
    public void login() throws ServiceException, NoSuchAlgorithmException {
        Account check = accountService.login("liangjiateng", "123456");
        assertNotNull(check);
        assertNotNull(check.getToken());
    }

    @Test
    public void signUp() throws ServiceException, NoSuchAlgorithmException {
        Account account = new Account();
        account.setUsername("liangjiateng1");
        account.setPassword("123456");
        Account check = accountService.signUp(account);
        assertNotNull(check);
        assertNotNull(check.getToken());
    }
}