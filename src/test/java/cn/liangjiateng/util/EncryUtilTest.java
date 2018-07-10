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
    public void getMd5() throws NoSuchAlgorithmException {
        assertEquals("eeeb64a357d7ff4aa37810d24354dd1d", EncryUtil.getMd5("!LJT5902879zz"));
    }

    @Test
    public void decryptBASE64() throws IOException {

    }

    @Test
    public void encryptBASE64() throws IOException, NoSuchAlgorithmException {
        Point3AcreService point3AcreService = new Point3AcreServiceImpl();
        point3AcreService.login("toxicaker", "!LJT5902879zz");
    }
}