package com.hishamx.service.user;

import com.hishamx.model.UserInfo;

/**
 * Created by XS on 2017/7/11.
 */
public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
