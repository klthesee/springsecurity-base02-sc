package com.zk.security.security;

import com.zk.security.utils.utils.R;
import com.zk.security.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token退出
 * 1.删除token
 * 2.根据token获取用户名，根据用户名在redis中将信息删除掉
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 1.从header获取token
        String token = request.getHeader("token");

        // 2 token不为空，移除token，从redis中删除token
        if (token!=null){
            //移除token
            tokenManager.removeToken(token);

            //删除redis中该用户的权限信息
            String username = tokenManager.getUserInfoFromToken(token);
            redisTemplate.delete(username);

        }
        ResponseUtil.out(response, R.ok());
    }

}
