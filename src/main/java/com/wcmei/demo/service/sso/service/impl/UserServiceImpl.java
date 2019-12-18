package com.wcmei.demo.service.sso.service.impl;

import com.wcmei.demo.service.sso.mapper.UserMapper;
import com.wcmei.demo.service.sso.service.UserService;
import com.wcmei.demo.service.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUserByUserAccount(String userAccount) {
        User user = new User();
        user.setUserAccount(userAccount);
        User oneUser = userMapper.selectOne(user);
        return oneUser;
    }
}
