package cn.liangjiateng.config;

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

    public int getSmallPage() {
        return smallPage;
    }

    public int getMediumPage() {
        return mediumPage;
    }

    public int getLargePage() {
        return largePage;
    }

}
