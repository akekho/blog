package cn.liangjiateng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Jiateng on 5/28/18.
 */
@ConfigurationProperties(prefix = "spring.druid")
public class DataSourceConfig {

    private String dbUrl;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String connectionProperties;


}
