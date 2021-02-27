package com.zk.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    /**
     * token的有效时长
     */
    private long tokenExpiration = 24*60*60*1000;
    /**
     * 编码私钥
     */
    private String tokenSignKey = "123456";

    /**
     * 1.根据用户名生成token
     */
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 2.根据token取出用户名
     */
    public String getUserInfoFromToken(String token) {
        String userInfo = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return userInfo;
    }

    /**
     * 3.删除token,做法：禁止前端传token
     */
    public void removeToken(String token) {

    }

}
