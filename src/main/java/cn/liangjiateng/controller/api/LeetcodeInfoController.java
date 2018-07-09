package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.LeetcodeInfo;
import cn.liangjiateng.service.LeetcodeInfoService;
import cn.liangjiateng.util.EncryUtil;
import cn.liangjiateng.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Leetcode服务
 * Created by Jiateng on 7/7/18.
 */
@RestController
@RequestMapping(value = "/api/services/leetcode", produces = "application/json")
public class LeetcodeInfoController {

    @Autowired
    private LeetcodeInfoService leetcodeInfoService;

    @PostMapping
    public JsonResponse insertLeetcodeInfo(@RequestBody String form) throws ServiceException {
        String username;
        String password;
        try {
            username = JsonUtil.get("username", form);
            password = JsonUtil.get("password", form);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "参数错误，详情：" + e.getMessage());
        }
        if (username == null || password == null) {
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "参数错误");
        }
        boolean loginCheck;
        try {
            loginCheck = leetcodeInfoService.login(username, password);
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.FAIL.getCode(), "Leetcode账号密码错误");
        }
        if (!loginCheck) {
            throw new ServiceException(ErrorCode.FAIL.getCode(), "Leetcode账号密码错误");
        }
        LeetcodeInfo leetcodeInfo = new LeetcodeInfo();
        leetcodeInfo.setUsername(username);
//        leetcodeInfo.setPassword(EncryUtil.encryptBASE64(password));
        long id = leetcodeInfoService.insertLeetcodeInfo(leetcodeInfo);
        return new JsonResponse(id);
    }

    @GetMapping("/{username}")
    public JsonResponse getLeetcodeInfoByUsername(@PathVariable String username) throws ServiceException {
        LeetcodeInfo leetcodeInfo = leetcodeInfoService.getLeetcodeInfoByUsername(username);
        return new JsonResponse(leetcodeInfo);
    }

    @GetMapping("/count")
    public JsonResponse countCurrentUsers() {
        return new JsonResponse(leetcodeInfoService.countLeetcodeInfo());
    }

    @PutMapping("/{username}/{password}/{operation}")
    public JsonResponse operateStatus(@PathVariable String username, @PathVariable String password, @PathVariable String operation) throws ServiceException {
        long id;
        LeetcodeInfo check = leetcodeInfoService.getLeetcodeInfoByUsername(username);
//        if (!check.getPassword().equals(EncryUtil.encryptBASE64(password)))
//            throw new ServiceException(ErrorCode.FAIL.getCode(), "Invalid Leetcode password");
        switch (operation) {
            case "resume":
                id = leetcodeInfoService.updateStatusByUsername(username, LeetcodeInfo.Status.RUNNING);
                break;
            case "stop":
                id = leetcodeInfoService.updateStatusByUsername(username, LeetcodeInfo.Status.STOPPED);
                break;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        }
        return new JsonResponse(id);
    }
}
