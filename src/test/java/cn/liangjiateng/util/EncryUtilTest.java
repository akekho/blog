package cn.liangjiateng.util;

import cn.liangjiateng.service.Point3AcreService;
import cn.liangjiateng.service.impl.Point3AcreServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * Created by Jiateng on 7/7/18.
 */
public class EncryUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMd5() {
    }

    @Test
    public void decryptBASE64() throws IOException {

    }

    @Test
    public void encryptBASE64() throws IOException, NoSuchAlgorithmException {
        Point3AcreService point3AcreService = new Point3AcreServiceImpl();
        point3AcreService.login("602689817@qq.com", EncryUtil.getMd5("LJT5902879ZZ"));
    }
}