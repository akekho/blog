package cn.liangjiateng.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 通用configuration
 * Created by Jiateng on 5/28/18.
 */
@Configuration
@ConfigurationProperties(prefix = "cn.liangjiateng.blog")
public class Config {

    private int smallPage;

    private int mediumPage;

    private int largePage;

    @Value("${server.head}")
    private String head;

    @Value("${server.host}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Value("${server.domain}")
    private String domain;

    private String storageHost;

    private String storageAccessKey;

    private String storageSecurityKey;

    private String storageZone;

    private String storageBucket;

    private String thumbUrl;

    private String slimUrl;

    public String getStorageBucket() {
        return storageBucket;
    }

    public void setStorageBucket(String storageBucket) {
        this.storageBucket = storageBucket;
    }

    public String getUrl(String url) {
        return head  + host + ":" + port + url;
    }

    public String getHead() {
        return head;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSmallPage() {
        return smallPage;
    }

    public int getMediumPage() {
        return mediumPage;
    }

    public int getLargePage() {
        return largePage;
    }

    public void setSmallPage(int smallPage) {
        this.smallPage = smallPage;
    }

    public void setMediumPage(int mediumPage) {
        this.mediumPage = mediumPage;
    }

    public void setLargePage(int largePage) {
        this.largePage = largePage;
    }

    public String getStorageHost() {
        return storageHost;
    }

    public String getStorageZone() {
        return storageZone;
    }

    public void setStorageZone(String storageZone) {
        this.storageZone = storageZone;
    }

    public void setStorageHost(String storageHost) {
        this.storageHost = storageHost;
    }

    public String getStorageAccessKey() {
        return storageAccessKey;
    }

    public void setStorageAccessKey(String storageAccessKey) {
        this.storageAccessKey = storageAccessKey;
    }

    public String getStorageSecurityKey() {
        return storageSecurityKey;
    }

    public void setStorageSecurityKey(String storageSecurityKey) {
        this.storageSecurityKey = storageSecurityKey;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getSlimUrl() {
        return slimUrl;
    }

    public void setSlimUrl(String slimUrl) {
        this.slimUrl = slimUrl;
    }
}
