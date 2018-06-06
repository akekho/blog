package cn.liangjiateng.util;

import cn.liangjiateng.config.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 结合七牛云的文件操作
 */
@Component
public final class CloudFileUtil {

    @Autowired
    Config config;

    private UploadManager uploadManager;

    private BucketManager bucketManager;

    private String token;

    private CloudFileUtil() {

    }

    private UploadManager getUploadManager() {
        if (uploadManager == null) {
            Configuration cfg = new Configuration(matchZone());
            this.uploadManager = new UploadManager(cfg);

        }
        return uploadManager;
    }

    private BucketManager getBucketManager() {
        if (bucketManager == null) {
            Configuration cfg = new Configuration(matchZone());
            Auth auth = Auth.create(config.getStorageAccessKey(), config.getStorageSecurityKey());
            bucketManager = new BucketManager(auth, cfg);
        }
        return bucketManager;
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
    public void upload(String path, String bucket, String fileName) throws IOException, FileUploadException {
        Response response = getUploadManager().put(path, fileName, getToken(bucket));
        DefaultPutRet putRet = JsonUtil.string2Bean(response.bodyString(), DefaultPutRet.class);
        if (!putRet.key.equals(fileName))
            throw new FileUploadException();
    }

    /**
     * 上传文件
     *
     * @param file     文件
     * @param bucket   云存储的组
     * @param fileName 文件名
     * @return
     * @throws IOException 重复文件名会报错
     */
    public void upload(File file, String bucket, String fileName) throws IOException, FileUploadException {
        Response response = getUploadManager().put(file, fileName, getToken(bucket));
        DefaultPutRet putRet = JsonUtil.string2Bean(response.bodyString(), DefaultPutRet.class);
        if (!putRet.key.equals(fileName))
            throw new FileUploadException();
    }

    /**
     * 流方式上传文件
     *
     * @param is       字节流
     * @param bucket   云存储的组
     * @param fileName 文件名
     * @throws IOException
     * @throws FileUploadException
     */
    public void upload(InputStream is, String bucket, String fileName) throws IOException, FileUploadException {
        Response response = getUploadManager().put(is, fileName, getToken(bucket), null, null);
        DefaultPutRet putRet = JsonUtil.string2Bean(response.bodyString(), DefaultPutRet.class);
        if (!putRet.key.equals(fileName))
            throw new FileUploadException();
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @param bucket   云存储的组
     * @throws QiniuException
     */
    public void delete(String fileName, String bucket) throws QiniuException {
        getBucketManager().delete(bucket, fileName);
    }

    /**
     * 根据配置匹配机房
     *
     * @return
     */
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
