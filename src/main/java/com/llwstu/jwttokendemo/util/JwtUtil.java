package com.llwstu.jwttokendemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {

    // Token一天后过期
    public static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;


    //检验Token是否正确
    public static boolean verify(String token, String username, String secret) {
        try {
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public static String sign(String username, String secret) {
        //现在系统的时间 + 一天
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //对密码进行加密
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static String getUserNameByToken(HttpServletRequest request)  {
        String token = request.getHeader("token");
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username")
                .asString();
    }

}
