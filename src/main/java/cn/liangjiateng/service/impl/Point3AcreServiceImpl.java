package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.Point3AcresMapper;
import cn.liangjiateng.pojo.DO.AcreInfo;
import cn.liangjiateng.service.Point3AcreService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by Jiateng on 7/9/18.
 */
@Service
public class Point3AcreServiceImpl implements Point3AcreService {
    @Autowired
    private Point3AcresMapper point3AcresMapper;

    @Override
    public long countAcreInfoByStatus(AcreInfo.Status status) {
        return point3AcresMapper.countAcreInfoByStatus(status.getVal());
    }

    @Override
    public long countAcreInfo() {
        return point3AcresMapper.countAcreInfo();
    }

    @Override
    public AcreInfo getAcreInfoByUsername(String username) throws ServiceException {
        AcreInfo acreInfo = point3AcresMapper.getAcreInfoByUsername(username);
        if (acreInfo == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "Username = " + username + "的一亩三分地信息不存在");
        return acreInfo;
    }

    @Override
    public long insertAcreInfo(AcreInfo acreInfo) throws ServiceException {
        if (acreInfo == null || acreInfo.getUsername() == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "acreInfo参数错误");
        AcreInfo check = point3AcresMapper.getAcreInfoByUsername(acreInfo.getUsername());
        if (check != null) {
            throw new ServiceException(ErrorCode.FAIL.getCode(), "Username = " + acreInfo.getUsername() + "的一亩三分地信息已存在");
        }
        return point3AcresMapper.insertAcreInfo(acreInfo);
    }

    @Override
    public long updateStatusByUsername(String username, AcreInfo.Status status) throws ServiceException {
        AcreInfo acreInfo = getAcreInfoByUsername(username);
        point3AcresMapper.updateStatusByUsername(acreInfo.getUsername(), status.getVal());
        return acreInfo.getId();
    }

    @Override
    public boolean login(String username, String password) throws IOException, ServiceException {
        HttpClient client = HttpClientBuilder.create().build();
        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("quickforward", "yes");
        data.put("'handlekey'", "ls");

        Map<String, String> headers = new HashMap<>();
        headers.put("origin", "http://www.1point3acres.com");
        headers.put("referer", "http://www.1point3acres.com/bbs/");
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
        headers.put("host", "www.1point3acres.com");

        HttpPost post = new HttpPost("http://www.1point3acres.com/bbs/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&inajax=1");
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
        String resStr = EntityUtils.toString(loginResp.getEntity());
        if (resStr.contains("密码错误次数过多"))
            throw new ServiceException(ErrorCode.FAIL.getCode(), "密码错误次数过多，请15分钟后重新登录");
        return !resStr.contains("登录失败");
    }
}
