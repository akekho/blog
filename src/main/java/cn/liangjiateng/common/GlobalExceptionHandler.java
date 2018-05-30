package cn.liangjiateng.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jiateng on 5/30/18.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse handleException(Exception e) {
        JsonResponse resp;
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            resp = new JsonResponse(se.getErrCode(), se.getMessage());
        } else {
            resp = new JsonResponse(ErrorCode.INTERNAL_ERR.getCode(), ErrorCode.INTERNAL_ERR.getMsg());
        }
        return resp;
    }

}