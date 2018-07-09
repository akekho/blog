package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.AcreInfo;


import java.io.IOException;

/**
 * 一亩三分地服务
 * Created by Jiateng on 7/9/18.
 */
public interface Point3AcreService {

    long countAcreInfoByStatus(AcreInfo.Status status);

    long countAcreInfo();

    AcreInfo getAcreInfoByUsername(String username) throws ServiceException;

    long insertAcreInfo(AcreInfo acreInfo) throws ServiceException;

    long updateStatusByUsername(String username, AcreInfo.Status status) throws ServiceException;

    /**
     * 一亩三分地登录检查
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password) throws IOException;
}
