package com.zk.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zk.aclservice.entity.User;
import com.zk.aclservice.mapper.PermissionMapper;
import com.zk.aclservice.service.PermissionService;
import com.zk.aclservice.service.UserService;
import com.zk.common.vo.UserVo;
import com.zk.security.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserVo curUserVo = new UserVo();
        BeanUtils.copyProperties(user,curUserVo);

        List<String> permissionValueList = permissionMapper.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(curUserVo);
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}
