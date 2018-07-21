package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.Log;
import cn.liangjiateng.util.Page;

import java.sql.Date;

/**
 * Created by Jiateng on 7/21/18.
 */
public interface LogService {

    Log getLogById(long id) throws ServiceException;

    Page<Log> listLogsByPid(String pid, Date datetime, int page, int pageSize) throws ServiceException;

    Page<Log> listLogsByPidAndLevel(String pid, Date datetime, Log.Level level, int page, int pageSize) throws ServiceException;

    Page<String> listDatetimesByPid(String pid, int page, int pageSize) throws ServiceException;

    long insertLog(Log log);
}
