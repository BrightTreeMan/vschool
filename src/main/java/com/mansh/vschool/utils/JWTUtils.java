package com.mansh.vschool.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mansh.vschool.custom.ParamConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JWTUtils {

    /**
     * 根据userName制作JWT
     * @param userName
     * @param secret
     * @return
     */
    public static String createTokenWithUserName(String userName,String secret) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Date now = new Date();

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userName",userName).
                withHeader(map)
                .withIssuer("mansh")
                .withSubject("vschool")
                .withIssuedAt(now)
                .withExpiresAt(CommonUtils.expireDate(now,0,0,0,0, ParamConst.JWT_TOKEN_LAST_TIME_OF_MINUTES,0));
        String token = builder.sign(Algorithm.HMAC256(secret));
        return token;
    }

    /**
     * 是否需要刷新JWT
     * 距离过期时间5分钟直接就刷新
     * @param token
     * @return
     */
    public static boolean shouldFreshToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().getTime() - System.currentTimeMillis() <= ParamConst.JWT_TOKEN_REFLUSH_INTERVAL;
    }

    /**
     * 获取token中对应key值
     * 没有的话返回""
     * @param token
     * @param name
     * @return
     */
    public static String get(String token,String name){
        DecodedJWT decodedJWT = JWT.decode(token);
        String re = "";
        Map claims = decodedJWT.getClaims();
        if(claims.containsKey(name)){
            re = decodedJWT.getClaim(name).asString();
        }
        return re;
    }

    /**
     * 是否过期 true 过期 false 未过期
     * @param token
     * @return
     */
    public static boolean isExpires(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().getTime() < System.currentTimeMillis();
    }



}
