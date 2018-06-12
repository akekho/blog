package cn.liangjiateng.common;

import cn.liangjiateng.config.Config;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

/**
 * 访问日志记录
 * Created by Jiateng on 5/30/18.
 */
@Aspect
@Component
public class GlobalLogHandler {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private Config config;

    @Pointcut("execution(public * cn.liangjiateng.controller..*.*(..))")
    public void webLog() {
    }

    @Pointcut("execution(public * cn.liangjiateng.mapper..*.*(..))")
    public void sqlLog() {

    }

    @Pointcut("execution(public * cn.liangjiateng.controller.views..*.*(..))")
    public void viewOutput() {

    }

    @Before("webLog()")
    public void webBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString()+
                " HTTP_METHOD : " + request.getMethod() +
                " IP : " + request.getRemoteAddr()+
                " CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+
                " ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void webAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret.toString());
    }

    @Before("viewOutput()")
    public void viewBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof ModelMap) {
                ModelMap map = (ModelMap) obj;
                map.addAttribute("host", config.getHead() + config.getHost() + ":" + config.getPort());
            }
        }
    }

}