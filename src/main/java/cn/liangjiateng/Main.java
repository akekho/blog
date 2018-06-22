package cn.liangjiateng;

import cn.liangjiateng.util.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("login", "jiateng.liang@nyu.edu");
        params.put("password", "LJT5902879ZZ");
        params.put("csrfmiddlewaretoken", "Ozk5QOWZfgKmxfwhqlEp8vxlw8CJNGSUtf8fywFcvHgRlCeVLqRzFTsRgBUQT8oy");

        Map<String, String> headers = new HashMap<>();
        headers.put("origin", "https://leetcode.com");
        headers.put("refer", "https://leetcode.com/accounts/login/");
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
        headers.put("cookie", "__cfduid=dee06f0ca25bbc03a789c6027b106e4761529657754; _ga=GA1.2.846109450.1529657756; _gid=GA1.2.1296679977.1529657756; csrftoken=Ozk5QOWZfgKmxfwhqlEp8vxlw8CJNGSUtf8fywFcvHgRlCeVLqRzFTsRgBUQT8oy");

        try {
            String res = HttpUtil.postForm("https://leetcode.com/accounts/login/", params, headers);
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
