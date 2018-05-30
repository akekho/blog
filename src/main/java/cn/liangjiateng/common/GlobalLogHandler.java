package cn.liangjiateng.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by Jiateng on 5/30/18.
 */
@Aspect
@Component
public class GlobalLogHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * cn.liangjiateng.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "webLog()", throwing = "ex")
    public void throwing(Throwable ex) {
        if (ex instanceof ServiceException) {
            ServiceException e = (ServiceException) ex;
            if (e.getErrCode() >= 400 && e.getErrCode() < 500)
                logger.warn(e.getMessage());
            else if(e.getErrCode() >= 500){
                logger.error(e.getMessage());
                logger.error(e.getCallStack());
            }else {
                logger.info(e.getMessage());
            }
        } else if (ex instanceof Exception) {
            logger.error("未知错误");
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            logger.error(sw.toString());
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

}