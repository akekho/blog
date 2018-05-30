package cn.liangjiateng.common;

import cn.liangjiateng.util.DateUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Jiateng on 5/29/18.
 */
public class ServiceException extends Exception {

    private int code;

    private String date = DateUtil.getCurrentTime(DateUtil.DateFormat.INTACT);

    public ServiceException(int errCode, String message) {
        super(message);
        this.code = errCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getErrCode() {
        return code;
    }

    public String getMessage() {
        return "[Error code]: " + code + "\n" +
                "[Message]: " + super.getMessage() +
                "\n [Time]: " + date;
    }

    public String getCallStack(){
        StringWriter sw = new StringWriter();
        this.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
