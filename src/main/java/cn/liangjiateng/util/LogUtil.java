package cn.liangjiateng.util;

import cn.liangjiateng.pojo.DO.Log;
import cn.liangjiateng.service.LogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 日志组件，刷入数据库和消息队列
 * Created by Jiateng on 7/21/18.
 */
@Component
public final class LogUtil {

    private Logger logger;

    private Set<Log> cache;

    private ExecutorService executorService;

    @Autowired
    private LogService logService;

    public LogUtil() {
        logger = Logger.getLogger("Blog");
        cache = new HashSet<>();
        executorService = Executors.newCachedThreadPool();
    }


    public void info(int errCode, String msg) {
        Log log = createLog(errCode, msg, Log.Level.INFO.getVal());
        cache.add(log);
    }

    public void warn(int errCode, String msg) {
        Log log = createLog(errCode, msg, Log.Level.WARN.getVal());
        cache.add(log);
    }

    public void error(int errCode, String msg) {
        Log log = createLog(errCode, msg, Log.Level.ERROR.getVal());
        cache.add(log);
    }

    public void fatal(int errCode, String msg) {
        Log log = createLog(errCode, msg, Log.Level.FATAL.getVal());
        cache.add(log);
    }

    /**
     * 异步将日志刷入数据库和文件
     */
    public void flush() {
        Runnable task = () -> {
            for (Log log : cache) {
                logService.insertLog(log);
                switch (log.getLevel()) {
                    case 0:
                        logger.info(log.getContent());
                        break;
                    case 1:
                        logger.warn(log.getContent());
                        break;
                    case 2:
                        logger.error(log.getContent());
                        break;
                    case 3:
                        logger.fatal(log.getContent());
                        break;
                }
            }
        };
        executorService.submit(task);
        cache.clear();
    }

    private Log createLog(int errCode, String msg, int level) {
        Log log = new Log();
        log.setPid("blog");
        log.setErrCode(errCode);
        log.setContent(msg);
        log.setLevel(level);
        log.setDatetime(new Date(System.currentTimeMillis()));
        return log;
    }
}
