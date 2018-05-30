package cn.liangjiateng.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jiateng on 5/29/18.
 */
public final class DateUtil {

    /**
     * 获取当前系统时间
     *
     * @param format 1. yyyy-MM-dd HH:mm:ss
     *               2. yyyy-MM-dd
     * @return string 格式类型
     */
    public static String getCurrentTime(DateFormat format) {
        String fmt;
        if (format == DateFormat.INTACT)
            fmt = "yyyy-MM-dd HH:mm:ss";
        else if (format == DateFormat.PARTIAL)
            fmt = "yyyy-MM-dd";
        else
            throw new IllegalArgumentException("DateFormat参数错误");
        SimpleDateFormat df = new SimpleDateFormat(fmt);
        return df.format(new Date());
    }

    /**
     * 获取当前系统时间
     *
     * @return unix时间戳
     */
    public static long getCurrentTime() {
        return new Date().getTime();
    }

    public enum DateFormat {
        INTACT,
        PARTIAL
    }
}
