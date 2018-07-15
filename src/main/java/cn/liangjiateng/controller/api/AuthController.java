package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.Account;
import cn.liangjiateng.service.AccountService;
import cn.liangjiateng.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Jiateng on 7/15/18.
 */
@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public JsonResponse login(@RequestBody String form) throws ServiceException, NoSuchAlgorithmException {
        String username;
        String password;
        try {
            username = JsonUtil.get("username", form);
            password = JsonUtil.get("password", form);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "参数错误，详情：" + e.getMessage());
        }
        Account account = accountService.login(username, password);
        return new JsonResponse(account.getToken());
    }

    @PostMapping("/signup")
    public JsonResponse signUp(@RequestBody String form) throws ServiceException, NoSuchAlgorithmException {
        String username;
        String password1;
        String password2;
        try {
            username = JsonUtil.get("username", form);
            password1 = JsonUtil.get("password1", form);
            password2 = JsonUtil.get("password2", form);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "参数错误，详情：" + e.getMessage());
        }
        if (username == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "账户名不能为空");
        if (password1 != null && !password1.equals(password2))
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "两次输入密码不相同");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password1);
        account = accountService.signUp(account);
        return new JsonResponse(account.getToken());
    }
}
