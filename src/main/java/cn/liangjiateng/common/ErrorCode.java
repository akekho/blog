package cn.liangjiateng.common;

/**
 * Created by Jiateng on 5/28/18.
 */
public enum ErrorCode {

    SUCCESS(200, "success"),

    FAIL(404, "fail"),

    PARAM_ERR(400, "parameter error"),

    INTERNAL_ERR(500, "internal error");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
