package com.hishamx.mapper;

import com.hishamx.model.UserInfo;

import java.util.List;

/**
 * Created by XS on 2017/7/11.
 */
public interface UserInfoPapper {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);

    public List<UserInfo> ListfindByUsername(String username);
}
