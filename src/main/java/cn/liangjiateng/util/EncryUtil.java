package cn.liangjiateng.util;


import java.util.Base64;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 * Created by Jiateng on 6/5/18.
 */
public final class EncryUtil {

    public static String getMd5(String pwd) throws NoSuchAlgorithmException {
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] btInput = pwd.getBytes();
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(btInput);
        byte[] md = mdInst.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = md5String[byte0 >>> 4 & 0xf];
            str[k++] = md5String[byte0 & 0xf];
        }
        return new String(str);

    }

    /**
     * BASE64解码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) throws IOException {
        byte[] bt;
        bt = Base64.getDecoder().decode(key);
        return new String(bt, "utf-8");
    }

    /**
     * BASE64编码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) {
        byte[] bt = key.getBytes();
        return Base64.getEncoder().encodeToString(bt).replaceAll("\n", "");
    }
}
