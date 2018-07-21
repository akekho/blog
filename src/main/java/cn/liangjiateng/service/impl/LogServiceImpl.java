package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.LogMapper;
import cn.liangjiateng.pojo.DO.Log;
import cn.liangjiateng.service.LogService;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiateng on 7/21/18.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public Log getLogById(long id) throws ServiceException {
        Log log = logMapper.getLogById(id);
        if (log == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "日志id=" + id + "不存在");
        return log;
    }

    @Override
    public Page<Log> listLogsByPid(String pid, Date datetime, int page, int pageSize) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = logMapper.countLogsByPid(pid, datetime);
        Page<Log> pageHolder = new Page<>(page, pageSize, cnt, null);
        pageHolder.setData(logMapper.listLogsByPid(pid, pageHolder, datetime));
        return pageHolder;
    }

    @Override
    public Page<Log> listLogsByPidAndLevel(String pid, Date datetime, Log.Level level, int page, int pageSize) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = logMapper.countLogsByPidAndLevel(pid, level.getVal(), datetime);
        Page<Log> pageHolder = new Page<>(page, pageSize, cnt, null);
        pageHolder.setData(logMapper.listLogsByPidAndLevel(pid, level.getVal(), pageHolder, datetime));
        return pageHolder;
    }

    @Override
    public Page<String> listDatetimesByPid(String pid, int page, int pageSize) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = logMapper.countDatetimes(pid);
        Page<String> pageHolder = new Page<>(page, pageSize, cnt, null);
        List<Date> res = logMapper.listDatetimesByPid(pid, pageHolder);
        List<String> dates = new ArrayList<>();
        for (Date date : res) {
            dates.add(date.toString());
        }
        pageHolder.setData(dates);
        return pageHolder;
    }

    @Override
    public long insertLog(Log log) {
        log.setDatetime(new Date(System.currentTimeMillis()));
        logMapper.insertLog(log);
        return log.getId();
    }
}
