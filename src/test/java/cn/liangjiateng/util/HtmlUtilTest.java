package cn.liangjiateng.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HtmlUtilTest {

    private String html;

    @Before
    public void setUp() throws Exception {
//        html = Jsoup.parse(new File("/Users/liangjiateng/Desktop/d.html"),"utf-8").toString();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void convertStyle() {
        System.out.println(HtmlUtil.convertStyle(html));
    }
}