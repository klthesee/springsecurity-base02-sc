package com.zk.aclservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zk.aclservice.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author chs
 * @since 2021-02-27
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据用户id查询权限列表
     * @param userId
     * @return
     */
    List<String> selectPermissionValueByUserId(String userId);
}
