package com.wcmei.demo.service.sso.config.service;

import com.google.common.collect.Lists;
import com.wcmei.demo.service.sso.service.PermissionService;
import com.wcmei.demo.service.sso.service.UserService;
import com.wcmei.demo.service.user.domain.Permission;
import com.wcmei.demo.service.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description 自定义用户认证与授权
 */
@Service
public class BaseUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    //获取账号信息
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        User baseUser = userService.selectUserByUserAccount(userAccount);
        if (Objects.isNull(baseUser)) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        List<Permission> permissions = permissionService.selectPermissionByRoleId(baseUser.getRoleId());
        //权限
        for (Permission permission : permissions) {
            String permissionName = permission.getPermissionName();
            grantedAuthorities.add(new SimpleGrantedAuthority(permissionName));
        }
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                baseUser.getUserAccount(), baseUser.getUserPassword(),
                        true, true, true, true, grantedAuthorities);
        return new BaseUserDetails(user,baseUser);
    }
}
