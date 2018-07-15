package cn.liangjiateng.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Jiateng on 7/15/18.
 */
public class TokenUtilTest {

    private String token;

    @Before
    public void setUp() throws Exception {
        this.token = TokenUtil.createToken("liangjiateng", "123456", 1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createToken() {
        String t = TokenUtil.createToken("liangjiateng", "123456", 1);
        this.token = t;
        assertNotNull(t);
    }

    @Test
    public void isExpire() {
        assertFalse(TokenUtil.isExpire(this.token));
    }

    @Test
    public void decodeToken() {
        Map<String, Object> map = TokenUtil.decodeToken(this.token);
        assertEquals("liangjiateng", map.get("username"));
        assertEquals("123456", map.get("password"));
    }
}