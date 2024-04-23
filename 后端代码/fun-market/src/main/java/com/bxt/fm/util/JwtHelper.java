package com.bxt.fm.util;

//token口令生成工具类

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtHelper {

    private static final long tokenExpiration = 1000 * 60 * 60 * 3;    //令牌的过期时间：3个小时
    private static final String tokenSignKey = "bxt-fun-market-2024-03-05-tokens";    //令牌签名的密钥，至少32位

    //指定解码后的密钥和密钥的算法为HMAC SHA-256
    private static final Key key = new SecretKeySpec(tokenSignKey.getBytes(), "HmacSHA256");

    //生成token字符串
    public static String createToken(String signId) {
        return Jwts.builder()
                    .subject("USER")   //面向的用户
                    .expiration(new Date(System.currentTimeMillis() + tokenExpiration))   //过期时间
                    .claim("signId", signId)   //自定义声明，在验证JWT时提取和使用这个值
                    .signWith(key)   //设置签名所需要的算法和密钥
                    .compressWith(Jwts.ZIP.GZIP)   //设置压缩算法为GZIP
                    .compact();
    }

    //从token字符串获取signId
    public static String getSignId(String token){
        if (!StringUtils.hasText(token)){
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token);
        Claims claims = claimsJws.getPayload();
        return String.valueOf(claims.get("signId"));
    }

    //判断token是否有效
    public static boolean isExpiration(String token){
        try {
            //没有过期，返回false
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        }catch (Exception e){
            //过期出现异常，返回true
            return true;
        }
    }

    //刷新token
    public String refreshToken(String token){
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(tokenSignKey)
                    .getPayload();
            refreshedToken = JwtHelper.createToken(getSignId(token));
        }catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }

}
