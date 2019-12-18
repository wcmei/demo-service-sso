package com.wcmei.demo.service.sso.service;

import com.wcmei.demo.service.user.domain.User;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
public interface UserService {
    //根据账号查找用户
    User selectUserByUserAccount(String userAccount);
}
