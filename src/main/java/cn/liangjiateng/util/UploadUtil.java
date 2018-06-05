package cn.liangjiateng.util;

import cn.liangjiateng.config.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public final class UploadUtil {

    @Autowired
    Config config;

    private UploadManager uploadManager;

    private String token;

    private UploadUtil() {

    }

    private UploadManager getUploadManager() {
        if (uploadManager == null) {
            Configuration cfg = new Configuration(matchZone());
            this.uploadManager = new UploadManager(cfg);

        }
        return uploadManager;
    }

    private String getToken(String bucket) {
        if (token == null) {
            Auth auth = Auth.create(config.getStorageAccessKey(), config.getStorageSecurityKey());
            this.token = auth.uploadToken(bucket);
        }
        return token;
    }

    /**
     * 上传文件
     *
     * @param path     文件路径
     * @param bucket   云存储的组
     * @param fileName 文件名
     * @return
     * @throws IOException 重复文件名会报错
     */
    public boolean upload(String path, String bucket, String fileName) throws IOException {
        Response response = getUploadManager().put(path, fileName, getToken(bucket));
        DefaultPutRet putRet = JsonUtil.string2Bean(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        return true;
    }

    /**
     * 上传文件
     *
     * @param file   文件
     * @param bucket 云存储的组
     * @param fileName 文件名
     * @return
     * @throws IOException 重复文件名会报错
     */
    public boolean upload(File file, String bucket, String fileName) throws IOException {
        Response response = getUploadManager().put(file, fileName, getToken(bucket));
        DefaultPutRet putRet = JsonUtil.string2Bean(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.hash);
        System.out.println(putRet.key);
        return true;
    }


    private Zone matchZone() {
        switch (config.getStorageZone()) {
            case "cn0":
                return Zone.zone0();
            case "cn1":
                return Zone.zone1();
            case "cn2":
                return Zone.zone2();
            case "na0":
                return Zone.zoneNa0();
        }
        return null;
    }
}
