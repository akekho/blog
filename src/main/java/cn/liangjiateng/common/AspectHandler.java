package cn.liangjiateng.common;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.service.AccountService;

import cn.liangjiateng.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * 访问日志记录
 * Created by Jiateng on 5/30/18.
 */
@Aspect
@Component
public class AspectHandler {

    @Autowired
    private LogUtil logUtil;

    @Autowired
    private Config config;

    @Autowired
    private AccountService accountService;

    private long APIstartTime;

    private long SQLstartTime;


    /**
     * http接口切面
     */
    @Pointcut("execution(public * cn.liangjiateng.controller..*.*(..))")
    public void webLog() {
    }


    /**
     * 视图层
     */
    @Pointcut("execution(public * cn.liangjiateng.controller.views..*.*(..))")
    public void viewOutput() {

    }

    /**
     * 后台权限控制
     */
    @Pointcut("execution(public * cn.liangjiateng.controller.views.back..*.*(..))")
    public void auth() {

    }

    @Before("auth()")
    public void authCheck(JoinPoint joinPoint) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        String username;
        if (token == null || (username = accountService.verifyToken(token)) == null) {
            HttpServletResponse response = attributes.getResponse();
            response.sendRedirect("/auth/login");
        } else {
            request.setAttribute("username", username);
        }
    }

    @Before("webLog()")
    public void webBefore(JoinPoint joinPoint) throws Throwable {
        this.APIstartTime = System.currentTimeMillis();
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void webAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        long endTime = System.currentTimeMillis();
        long interval = endTime - APIstartTime;
        // 处理完请求，返回内容
        if (ret != null)
            if (interval < 500)
                logUtil.info(ErrorCode.SUCCESS.getCode(), "URL : " + request.getRequestURL().toString() +
                        "\nHTTP_METHOD : " + request.getMethod() +
                        "\nIP : " + request.getRemoteAddr() +
                        "\nCLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                        "\nARGS : " + Arrays.toString(joinPoint.getArgs()) + "\nRESPONSE: " + ret.toString() + "\nTime: " + interval + "ms");
            else if (interval < 1000)
                logUtil.warn(ErrorCode.TIME_OUT.getCode(), "URL : " + request.getRequestURL().toString() +
                        "\nHTTP_METHOD : " + request.getMethod() +
                        "\nIP : " + request.getRemoteAddr() +
                        "\nCLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                        "\nARGS : " + Arrays.toString(joinPoint.getArgs()) + "\nRESPONSE: " + ret.toString() + "\nTime: " + interval + "ms");
            else
                logUtil.error(ErrorCode.TIME_OUT.getCode(), "URL : " + request.getRequestURL().toString() +
                        "\nHTTP_METHOD : " + request.getMethod() +
                        "\nIP : " + request.getRemoteAddr() +
                        "\nCLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                        "\nARGS : " + Arrays.toString(joinPoint.getArgs()) + "\nRESPONSE: " + ret.toString() + "\nTime: " + interval + "ms");
        logUtil.flush();
    }

    @Before("viewOutput()")
    public void viewBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof ModelMap) {
                ModelMap map = (ModelMap) obj;
                if (config.getDomain() != null && !config.getDomain().equals("")) {
                    map.addAttribute("host", config.getHead() + config.getDomain());
                } else {
                    map.addAttribute("host", config.getHead() + config.getHost() + ":" + config.getPort());
                }
            }
        }
    }
}