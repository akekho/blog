package cn.liangjiateng.common;

import cn.liangjiateng.thrift_client.job.JobServiceException;
import org.apache.log4j.Logger;
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

    private Logger logger = Logger.getLogger(getClass());

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
                logger.warn(e.getLogMessage());
            else if (e.getErrCode() >= 500) {
                logger.error(e.getLogMessage());
                logger.error(e.getCallStack());
            } else {
                logger.info(e.getLogMessage());
            }
        } else if (ex instanceof MissingServletRequestParameterException ||
                ex instanceof MethodArgumentTypeMismatchException ||
                ex instanceof HttpRequestMethodNotSupportedException) {
            logger.warn(ex.getMessage());
        } else if (ex instanceof Exception) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            logger.error("未知错误：" + sw.toString());
        }
    }

}