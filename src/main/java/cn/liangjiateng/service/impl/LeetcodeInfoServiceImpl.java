package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.LeetcodeInfoMapper;
import cn.liangjiateng.pojo.DO.LeetcodeInfo;
import cn.liangjiateng.service.LeetcodeInfoService;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;

/**
 * Created by Jiateng on 7/7/18.
 */
@Service
public class LeetcodeInfoServiceImpl implements LeetcodeInfoService {

    @Autowired
    private LeetcodeInfoMapper leetcodeInfoMapper;

    @Override
    public long countLeetcodeInfoByStatus(LeetcodeInfo.Status status) {
        return leetcodeInfoMapper.countLeetcodeInfoByStatus(status.getVal());
    }

    @Override
    public long countLeetcodeInfo() {
        return leetcodeInfoMapper.countLeetcodeInfo();
    }

    @Override
    public LeetcodeInfo getLeetcodeInfoByUsername(String username) throws ServiceException {
        LeetcodeInfo leetcodeInfo = leetcodeInfoMapper.getLeetcodeInfoByUsername(username);
        if (leetcodeInfo == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "Username = " + username + "的leetcode_info不存在");
        return leetcodeInfo;
    }

    @Override
    public long insertLeetcodeInfo(LeetcodeInfo leetcodeInfo) throws ServiceException {
        if (leetcodeInfo == null || leetcodeInfo.getUsername() == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "leetcodeinfo参数错误");
        LeetcodeInfo check = leetcodeInfoMapper.getLeetcodeInfoByUsername(leetcodeInfo.getUsername());
        if (check != null) {
            throw new ServiceException(ErrorCode.FAIL.getCode(), "Username = " + leetcodeInfo.getUsername() + "的leetcodeInfo已存在");
        }
        return leetcodeInfoMapper.insertLeetcodeInfo(leetcodeInfo);
    }

    @Override
    public long updateStatusByUsername(String username, LeetcodeInfo.Status status) throws ServiceException {
        LeetcodeInfo leetcodeInfo = getLeetcodeInfoByUsername(username);
        leetcodeInfoMapper.updateStatusByUsername(leetcodeInfo.getUsername(), status.getVal());
        return leetcodeInfo.getId();
    }

    @Override
    public boolean login(String username, String password) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://leetcode.com");
        HttpResponse response = client.execute(request);

        String cookie;
        String token = null;
        for (Header header : response.getHeaders("Set-Cookie")) {
            cookie = header.getValue();
            token = getToken(cookie);
            if (token != null) {
                break;
            }
        }

        Map<String, String> data = new HashMap<>();
        data.put("login", username);
        data.put("password", password);
        data.put("csrfmiddlewaretoken", token);
        Map<String, String> headers = new HashMap<>();
        headers.put("origin", "https://leetcode.com");
        headers.put("referer", "https://leetcode.com/accounts/login/");
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
        headers.put("cookie", "__cfduid=df5e01f1925b204689f6febcc69b11f9e1506110172; __stripe_mid=5153ea41-e0e8-4636-8b13-6f985ef6ffb7; _ga=GA1.2.377648857.1510501634; __atuvc=5%7C17%2C0%7C18%2C11%7C19%2C0%7C20%2C9%7C21; csrftoken=" + token + "; _gid=GA1.2.1391822110.1529675267");

        HttpPost post = new HttpPost("https://leetcode.com/accounts/login/");
        for (Map.Entry<String, String> header : headers.entrySet()) {
            post.setHeader(header.getKey(), header.getValue());
        }
        List<NameValuePair> nvps = new ArrayList<>();
        for (Iterator iter = data.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String value = String.valueOf(data.get(name));
            nvps.add(new BasicNameValuePair(name, value));
        }
        post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        HttpResponse loginResp = client.execute(post);
        return loginResp.getStatusLine().getStatusCode() == 302;
    }

    private String getToken(String cookie) {
        String token;
        String[] strs = cookie.split(" ");
        for (String msg : strs) {
            if (msg.startsWith("csrftoken")) {
                token = msg.split("=")[1].replaceAll(";", "");
                return token;
            }
        }
        return null;
    }
}
