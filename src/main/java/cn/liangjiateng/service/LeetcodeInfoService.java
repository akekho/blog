package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.LeetcodeInfo;

import java.io.IOException;

/**
 * Leetcode服务
 * Created by Jiateng on 7/7/18.
 */
public interface LeetcodeInfoService {

    long countLeetcodeInfoByStatus(LeetcodeInfo.Status status);

    long countLeetcodeInfo();

    LeetcodeInfo getLeetcodeInfoByUsername(String username) throws ServiceException;

    long insertLeetcodeInfo(LeetcodeInfo leetcodeInfo) throws ServiceException;

    long updateStatusByUsername(String username, LeetcodeInfo.Status status) throws ServiceException;

    /**
     * leetcode登录检查
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password) throws IOException;


}
