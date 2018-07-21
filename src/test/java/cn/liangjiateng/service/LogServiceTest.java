package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.LogMapper;
import cn.liangjiateng.pojo.DO.Log;
import cn.liangjiateng.util.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by Jiateng on 7/21/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Autowired
    private LogMapper logMapper;

    private long id;

    @Before
    public void setUp() throws Exception {
        tearDown();
        for (int i = 0; i < 500; i++) {
            Log log = new Log();
            log.setPid("blog");
            log.setContent("error");
            log.setLevel(0);
            if (i % 3 == 0)
                log.setLevel(1);
            else if (i % 5 == 0) {
                log.setLevel(2);
            } else if (i % 7 == 0) {
                log.setLevel(3);
            }
            log.setErrCode(400);
            log.setDatetime(new Date(System.currentTimeMillis()));
            logMapper.insertLog(log);
            id = log.getId();
        }
    }

    @After
    public void tearDown() throws Exception {
        logMapper.deleteAll();
    }

    @Test
    public void getLogById() throws ServiceException {
        Log log = logService.getLogById(id);
        assertNotNull(log);
    }

    @Test
    public void listLogsByPid() throws ServiceException {
        Page<Log> page = logService.listLogsByPid("blog", new Date(System.currentTimeMillis()), 1, 20);
        assertEquals(500, page.getMaxCount());
        assertEquals(20, page.getData().size());
        assertNotNull(page.getData().get(0));
    }

    @Test
    public void listLogsByPidAndLevel() throws ServiceException {
        Page<Log> page = logService.listLogsByPidAndLevel("blog", new Date(System.currentTimeMillis()), Log.Level.ERROR, 1, 20);
        assertEquals(66, page.getMaxCount());
        assertEquals(20, page.getData().size());
        assertNotNull(page.getData().get(0));
    }

    @Test
    public void listDatetimesByPid() throws ServiceException {
        Page<String> page = logService.listDatetimesByPid("blog", 1, 20);
        assertEquals(1, page.getMaxCount());
    }

    @Test
    public void insertLog() {
        Log log = new Log();
        log.setPid("blog");
        log.setContent("test");
        log.setLevel(0);
        log.setDatetime(new Date(System.currentTimeMillis()));
        log.setErrCode(400);
        logService.insertLog(log);
        assertEquals("test", logMapper.getLogById(log.getId()).getContent());
    }
}