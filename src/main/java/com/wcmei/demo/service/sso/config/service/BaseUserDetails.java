package com.wcmei.demo.service.sso.config.service;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
public class BaseUserDetails implements org.springframework.security.core.userdetails.UserDetails, CredentialsContainer {

    private final User user;
    private final com.wcmei.demo.service.user.domain.User baseUser;

    public BaseUserDetails(User user, com.wcmei.demo.service.user.domain.User baseUser) {
        this.user = user;
        this.baseUser = baseUser;
    }

    @Override
    public void eraseCredentials() {
        //清除密码
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //获取已授予的权限
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        //获取密码
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        //获取账号（用户名）
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        //账号是否未过期
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        //账号是否已未锁定
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //账号凭证是否未过期
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        //账号是否启用
        return user.isEnabled();
    }
}
