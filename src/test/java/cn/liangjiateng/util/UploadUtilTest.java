package cn.liangjiateng.util;

import cn.liangjiateng.config.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadUtilTest {

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private Config config;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void uploadPath() throws IOException {
        uploadUtil.upload("/Users/liangjiateng/Desktop/4.png", config.getStorageBucket(), "ttt.png");
    }

    @Test
    public void uploadFile() throws IOException {
        File file = new File("/Users/liangjiateng/Desktop/4.png");
        uploadUtil.upload(file, config.getStorageBucket(), "image/ttt.png");
    }
}