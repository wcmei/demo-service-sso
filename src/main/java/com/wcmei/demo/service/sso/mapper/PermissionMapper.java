package com.wcmei.demo.service.sso.mapper;

import com.wcmei.demo.commons.mybatis.baseMapper.MyMapper;
import com.wcmei.demo.service.user.domain.Permission;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
public interface PermissionMapper extends MyMapper<Permission> {

    //根据角色id查询权限
    List<Permission> selectPermissionByRoleId(Long roleId);
}
