package com.txhl.user.service.impl;

import com.txhl.user.entity.UserInfo;
import com.txhl.user.repository.UserInfoRepository;
import com.txhl.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层
 *
 * @author Administrator
 * @create 2018-11-10 21:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {

        return userInfoRepository.findByOpenid(openid);
    }
}
