package com.txhl.user.service;

import com.txhl.user.entity.UserInfo;

public interface UserService {

    /**
     * 通过openid查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
