package cn.liangjiateng.thrift_client;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.thrift_client.job.JobRPCService;
import cn.liangjiateng.thrift_client.job.JobServiceException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThriftClient {

    @Autowired
    private Config config;

    /**
     * 获取定时任务rpc客户端的静态代理
     *
     * @return
     */
    public class JobClient extends JobRPCService.Client{

        private TTransport transport;

        private JobRPCService.Client client;

        public JobClient() {
            super();
            transport = new TSocket(config.getRpcHost(), config.getRpcPort(), config.getRpcTimeout());
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new JobRPCService.Client(protocol);
        }

        public void startJob(String jobId) throws TException {
            transport.open();
            client.start_job(jobId);
            transport.close();
        }

        public void stopJob(String jobId){

        }
    }
}
