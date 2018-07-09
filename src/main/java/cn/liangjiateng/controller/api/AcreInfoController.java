package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.AcreInfo;
import cn.liangjiateng.service.Point3AcreService;
import cn.liangjiateng.util.EncryUtil;
import cn.liangjiateng.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 一亩三分地服务
 * Created by Jiateng on 7/7/18.
 */
@RestController
@RequestMapping(value = "/api/services/1point3acres", produces = "application/json")
public class AcreInfoController {

    @Autowired
    private Point3AcreService point3AcreService;

    @PostMapping
    public JsonResponse insertAcreInfo(@RequestBody String form) throws ServiceException, NoSuchAlgorithmException {
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
            loginCheck = point3AcreService.login(username, EncryUtil.getMd5(password));
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.FAIL.getCode(), "一亩三分地账号密码错误");
        }
        if (!loginCheck) {
            throw new ServiceException(ErrorCode.FAIL.getCode(), "一亩三分地账号密码错误");
        }
        AcreInfo acreInfo = new AcreInfo();
        acreInfo.setUsername(username);
        acreInfo.setPassword(EncryUtil.getMd5(password));
        long id = point3AcreService.insertAcreInfo(acreInfo);
        return new JsonResponse(id);
    }

    @GetMapping("/{username}")
    public JsonResponse getAcreInfoByUsername(@PathVariable String username) throws ServiceException {
        AcreInfo acreInfo = point3AcreService.getAcreInfoByUsername(username);
        return new JsonResponse(acreInfo);
    }

    @GetMapping("/count")
    public JsonResponse countCurrentUsers() {
        return new JsonResponse(point3AcreService.countAcreInfo());
    }

    @PutMapping("/{username}/{password}/{operation}")
    public JsonResponse operateStatus(@PathVariable String username, @PathVariable String password, @PathVariable String operation) throws ServiceException, NoSuchAlgorithmException {
        long id;
        AcreInfo check = point3AcreService.getAcreInfoByUsername(username);
        if (!check.getPassword().equals(EncryUtil.getMd5(password)))
            throw new ServiceException(ErrorCode.FAIL.getCode(), "一亩三分地密码错误");
        switch (operation) {
            case "resume":
                id = point3AcreService.updateStatusByUsername(username, AcreInfo.Status.RUNNING);
                break;
            case "stop":
                id = point3AcreService.updateStatusByUsername(username, AcreInfo.Status.STOPPED);
                break;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        }
        return new JsonResponse(id);
    }
}
