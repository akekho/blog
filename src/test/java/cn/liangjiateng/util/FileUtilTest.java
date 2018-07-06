package cn.liangjiateng.util;

import cn.liangjiateng.config.Config;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUtilTest {

    @Autowired
    private CloudFileUtil cloudFileUtil;
    @Autowired
    private Config config;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void pdfImage() throws IOException, FileUploadException {
        InputStream is = FileUtil.pdf2Image("/Users/liangjiateng/Desktop/1.pdf");
        cloudFileUtil.upload(is, config.getStorageBucket(), "test_cover.png");
    }
}