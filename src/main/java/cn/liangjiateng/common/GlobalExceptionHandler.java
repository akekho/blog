package cn.liangjiateng.common;

import cn.liangjiateng.thrift_client.job.JobServiceException;
import cn.liangjiateng.util.LogUtil;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * Created by Jiateng on 5/30/18.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private LogUtil logUtil;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse handleException(Exception e) {
        JsonResponse resp;
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            resp = new JsonResponse(se.getErrCode(), se.getMsg());
        } else if (e instanceof MissingServletRequestParameterException) {
            resp = new JsonResponse(ErrorCode.PARAM_ERR.getCode(), e.getMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            resp = new JsonResponse(ErrorCode.PARAM_ERR.getCode(), e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            resp = new JsonResponse(ErrorCode.PARAM_ERR.getCode(), e.getMessage());
        } else if (e instanceof JobServiceException) {
            JobServiceException je = (JobServiceException) e;
            resp = new JsonResponse(ErrorCode.FAIL.getCode(), je.msg);
        } else if (e instanceof TTransportException) {
            resp = new JsonResponse(ErrorCode.INTERNAL_ERR.getCode(), "RPC连接错误");
        } else {
            resp = new JsonResponse(ErrorCode.INTERNAL_ERR.getCode(), ErrorCode.INTERNAL_ERR.getMsg());
        }
        doLog(e);
        return resp;
    }

    /**
     * 输出日志
     *
     * @param ex
     */
    private void doLog(Throwable ex) {
        if (ex instanceof ServiceException) {
            ServiceException e = (ServiceException) ex;
            if (e.getErrCode() >= 400 && e.getErrCode() < 500 || e.getErrCode() == -1)
                logUtil.warn(e.getErrCode(), e.getLogMessage());
            else if (e.getErrCode() >= 500) {
                logUtil.fatal(e.getErrCode(), e.getLogMessage());
            } else {
                logUtil.info(e.getErrCode(), e.getMsg());
            }
        } else if (ex instanceof JobServiceException) {
            JobServiceException jse = (JobServiceException) ex;
            logUtil.warn(jse.code, jse.getMsg());
        } else if (ex instanceof MissingServletRequestParameterException ||
                ex instanceof MethodArgumentTypeMismatchException ||
                ex instanceof HttpRequestMethodNotSupportedException) {
            logUtil.warn(ErrorCode.FAIL.getCode(), ex.getMessage());
        } else if (ex instanceof Exception) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            logUtil.fatal(ErrorCode.INTERNAL_ERR.getCode(), "Unknown error：" + sw.toString());
        }
        logUtil.flush();
    }

}