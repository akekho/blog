package cn.liangjiateng.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token生成校验工具
 * Created by Jiateng on 7/15/18.
 */
public final class TokenUtil {

    private static final String SECURITY_KEY = "ef04dab823ae246f35535e5276ced886";

    public static final long ONE_HOUR = 60;

    public static final long YWO_HOUR = 120;

    public static final long ONE_DAY = 1440;

    public static final long THREE_DAY = 4320;

    public static final long ONE_WEEK = 10080;

    /**
     * 生成token
     *
     * @param username
     * @param password
     * @param expire   分钟
     * @return
     */
    public static String createToken(String username, String password, long expire) {
        Algorithm algorithm = Algorithm.HMAC256(SECURITY_KEY);
        return JWT.create().withIssuer("liangjiateng").withExpiresAt(new Date(System.currentTimeMillis() + expire * 1000)).
                withClaim("username", username).
                withClaim("password", password).
                sign(algorithm);
    }

    /**
     * token是否失效
     *
     * @param token
     * @return
     */
    public static boolean isExpire(String token) {
        Map<String, Object> map = decodeToken(token);
        long time = (long) map.get("date");
        return System.currentTimeMillis() > time;
    }

    /**
     * 解密token
     *
     * @param token
     * @return {username, password, date}
     */
    public static Map<String, Object> decodeToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Object> res = new HashMap<>();
        Map<String, Claim> map = jwt.getClaims();
        long time = jwt.getExpiresAt().getTime();
        res.put("username", map.get("username").asString());
        res.put("password", map.get("password").asString());
        res.put("date", time);
        return res;
    }
}
