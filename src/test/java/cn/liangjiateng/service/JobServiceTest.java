package cn.liangjiateng.service;

import org.apache.thrift.TException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 该单元测试需要开启RPC服务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {

    @Autowired
    private JobService jobService;

    @Before
    public void setUp() throws Exception {
        jobService.start_scheduler();
    }

    @After
    public void tearDown() throws Exception {
//        jobService.stop_scheduler();
    }

    @Test
    public void start_scheduler() throws TException {
        jobService.stop_scheduler();
        jobService.start_scheduler();
    }

    @Test
    public void stop_scheduler() throws TException {
        jobService.stop_scheduler();
    }

    @Test
    public void pause_scheduler() throws TException {
        jobService.pause_scheduler();
    }

    @Test
    public void resume_scheduler() throws TException {
        jobService.pause_scheduler();
        jobService.resume_scheduler();
    }

    @Test
    public void start_job() throws TException {
        jobService.start_job("test");
    }

    @Test
    public void stop_job() throws TException {
        jobService.stop_job("test");
    }

    @Test
    public void pause_job() throws TException {
        jobService.pause_job("test");
    }

    @Test
    public void modify_job() throws TException {
        String config = "{\"name\": \"test1\"}";
        jobService.modify_job("test", config);
    }

    @Test
    public void submit_job() {
    }
}