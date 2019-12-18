package com.wcmei.demo.service.sso.service.impl;

import com.wcmei.demo.service.sso.mapper.PermissionMapper;
import com.wcmei.demo.service.sso.service.PermissionService;
import com.wcmei.demo.service.user.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectPermissionByRoleId(Long roleId) {
        List<Permission> permissions = permissionMapper.selectPermissionByRoleId(roleId);
        return permissions;
    }
}
