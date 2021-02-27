package com.zk.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zk.security.entity.SecurityUser;
import com.zk.security.entity.User;
import com.zk.security.security.TokenManager;
import com.zk.security.utils.utils.R;
import com.zk.security.utils.utils.ResponseUtil;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 认证过滤器
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(TokenManager tokenManager, RedisTemplate redisTemplate, AuthenticationManager authenticationManager) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login","POST"));
    }

    /**
     * 获取表单提交的用户名和密码
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 获取表单提交的数据
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),new ArrayList<>()));
    }

    /**
     * 认证成功调用的方法
     * @param request
     * @param response
     * @param chain
     * @param authResult 认证之后的内容
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityUser user = (SecurityUser)authResult.getPrincipal();
        // 根据用户名创建token
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        // 把用户名和对应的权限存到redis中
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(),user.getAuthorities());

        ResponseUtil.out(response, R.ok().data("token",token));
    }

    /**
     * 认证失败调用的方法
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error().message("认证失败"));

    }
}
