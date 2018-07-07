package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.LeetcodeInfoMapper;

import cn.liangjiateng.pojo.DO.LeetcodeInfo;
import cn.liangjiateng.service.impl.LeetcodeInfoServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Jiateng on 7/7/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LeetcodeInfoServiceTest {

    @Autowired
    private LeetcodeInfoMapper leetcodeInfoMapper;

    @Autowired
    private LeetcodeInfoService leetcodeInfoService;

    private String username;

    @Before
    public void setUp() throws Exception {
        tearDown();
        for (int i = 0; i < 100; i++) {
            LeetcodeInfo leetcodeInfo = new LeetcodeInfo();
            leetcodeInfo.setUsername("username" + i);
            leetcodeInfo.setPassword("d31cac" + i);
            if (i % 3 == 0)
                leetcodeInfo.setStatus(0);
            username = "username" + i;
            leetcodeInfoMapper.insertLeetcodeInfo(leetcodeInfo);
        }
    }

    @After
    public void tearDown() throws Exception {
        leetcodeInfoMapper.deleteAllLeetcodeInfo();
    }

    @Test
    public void countLeetcodeInfoByStatus() {
        assertEquals(34, leetcodeInfoService.countLeetcodeInfoByStatus(LeetcodeInfo.Status.STOPPED));
    }

    @Test
    public void countLeetcodeInfo() {
        assertEquals(100, leetcodeInfoService.countLeetcodeInfo());
    }

    @Test
    public void getLeetcodeInfoByUsername() throws ServiceException {
        LeetcodeInfo leetcodeInfo = leetcodeInfoService.getLeetcodeInfoByUsername(username);
        assertEquals(username, leetcodeInfo.getUsername());
    }

    @Test
    public void insertLeetcodeInfo() {

    }

    @Test
    public void updateStatusByUsername() throws ServiceException {
        leetcodeInfoService.updateStatusByUsername(username, LeetcodeInfo.Status.DELETED);
        LeetcodeInfo leetcodeInfo = leetcodeInfoService.getLeetcodeInfoByUsername(username);
        assertEquals(-1, (int) leetcodeInfo.getStatus());
    }

    @Test
    public void login() throws IOException {
        LeetcodeInfoService leetcodeInfoService = new LeetcodeInfoServiceImpl();
        leetcodeInfoService.login("luxuan.wang@nyu.edu","525=xuan");
    }
}