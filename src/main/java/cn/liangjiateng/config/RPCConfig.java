package cn.liangjiateng.config;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rpc配置
 */
@Configuration
public class RPCConfig {

    @Autowired
    private Config config;

    @Bean
    public TProtocol protocolConfig(TTransport transport) {
        return new TBinaryProtocol(transport);
    }

    @Bean
    public TTransport transportConfig() {
        return new TSocket(config.getRpcHost(), config.getRpcPort(), config.getRpcTimeout());
    }
}
