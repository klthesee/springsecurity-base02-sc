package com.zk.aclservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zk.aclservice.entity.Permission;
import com.zk.aclservice.mapper.PermissionMapper;
import com.zk.aclservice.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author chs
 * @since 2021-02-27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
