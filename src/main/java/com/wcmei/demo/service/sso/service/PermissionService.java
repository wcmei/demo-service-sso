package com.wcmei.demo.service.sso.service;

import com.wcmei.demo.service.user.domain.Permission;

import java.util.List;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
public interface PermissionService {

    //根据角色id查询权限
    List<Permission> selectPermissionByRoleId(Long roleId);
}
