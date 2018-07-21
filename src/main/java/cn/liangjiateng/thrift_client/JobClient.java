package cn.liangjiateng.thrift_client;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.thrift_client.job.JobRPCService;
import cn.liangjiateng.util.LogUtil;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LogUtil logUtil;

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
        logUtil.info(ErrorCode.SUCCESS.getCode(),
                "RPC CALL : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                        "\nARGS : " + Arrays.toString(joinPoint.getArgs()));
        try {
            this.transport.open();
        } catch (TTransportException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logUtil.fatal(ErrorCode.INTERNAL_ERR.getCode(), "未知错误：" + sw.toString());
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
        long interval = endTime - startTime;
        // 处理完请求，返回内容
        if (ret == null) {
            if (interval < 500)
                logUtil.info(ErrorCode.SUCCESS.getCode(), "RPC RESPONSE : void\nRPC TIME: " + interval + "ms");
            else if (interval < 1000)
                logUtil.warn(ErrorCode.TIME_OUT.getCode(), "RPC RESPONSE : void\nRPC TIME: " + interval + "ms");
            else
                logUtil.error(ErrorCode.TIME_OUT.getCode(), "RPC RESPONSE : void\nRPC TIME: " + interval + "ms");
        } else {
            if (interval < 500)
                logUtil.info(ErrorCode.SUCCESS.getCode(), "RPC RESPONSE : " + ret.toString() + "\nRPC TIME: " + interval + "ms");
            else if (interval < 1000)
                logUtil.warn(ErrorCode.TIME_OUT.getCode(), "RPC RESPONSE : " + ret.toString() + "\nRPC TIME: " + interval + "ms");
            else
                logUtil.error(ErrorCode.TIME_OUT.getCode(), "RPC RESPONSE : " + ret.toString() + "\nRPC TIME: " + interval + "ms");
        }
    }

}
