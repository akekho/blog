package cn.liangjiateng.thrift_client;

import cn.liangjiateng.thrift_client.job.JobRPCService;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * 定时任务系统Thrift客户端
 */
@Component
@Aspect
public class JobClient extends JobRPCService.Client {

    private Logger logger = Logger.getLogger(getClass());

    private long startTime;

    private TTransport transport;

    public JobClient(TProtocol protocol, TTransport transport) {
        super(protocol);
        this.transport = transport;
    }

    @Pointcut("execution(public * cn.liangjiateng.thrift_client..*.*(..))")
    private void rpcLog() {

    }

    @Before("rpcLog()")
    private void before(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        logger.info(
                " RPC CALL : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                        " ARGS : " + Arrays.toString(joinPoint.getArgs()));
        try {
            this.transport.open();
        } catch (TTransportException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error("未知错误：" + sw.toString());
        }

    }

    @AfterThrowing(throwing = "ex", pointcut = "rpcLog()")
    private void closeTransport(Throwable ex) {
        this.transport.close();
    }

    @AfterReturning(returning = "ret", pointcut = "rpcLog()")
    private void afterReturning(Object ret) throws Throwable {
        this.transport.close();
        long endTime = System.currentTimeMillis();
        // 处理完请求，返回内容
        if(ret == null)
            logger.info("RESPONSE : void");
        else
            logger.info("RESPONSE : " + ret.toString());
        logger.info("TIME : " + (endTime - startTime) / 1000.0f + "s");
    }

}
