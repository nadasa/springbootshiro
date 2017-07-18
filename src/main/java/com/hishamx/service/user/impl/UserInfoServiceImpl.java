package com.hishamx.service.user.impl;


import com.hishamx.mapper.UserInfoPapper;
import com.hishamx.model.UserInfo;
import com.hishamx.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XS on 2017/7/11.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoPapper userInfoPapper;

    @Override
    public UserInfo findByUsername(String username) {
      return   userInfoPapper.ListfindByUsername(username).get(0);
    }

//    @Override
//    public UserInfo findByUsername(String username) {
//        System.out.println("UserInfoServiceImpl.findByUsername()");
////        return userInfoDao.findByUsername(username);
//        UserInfo userInfo=new UserInfo();
//        userInfo.setName("admin");
//        userInfo.setPassword("d3c59d25033dbf980d29554025c23a75");
//        userInfo.setSalt("0");
//        userInfo.setUid(1);
//        userInfo.setName("管理员");
//
//        List<SysRole> lstRole=new ArrayList<SysRole>();
//        SysRole sysRole=new SysRole();
//        sysRole.setId(1);
//        sysRole.setAvailable(true);
//        sysRole.setDescription("管理员");
//        sysRole.setRole("admin");
//        lstRole.add(sysRole);
//
//        List<SysPermission> lstSysPermission=new ArrayList<SysPermission>();
//        SysPermission sysPermission=new SysPermission();
//        sysPermission.setAvailable(true);
//        sysPermission.setName("用户管理");
//        sysPermission.setId(1);
//        sysPermission.setParentId(0L);
//        sysPermission.setPermission("userInfo:view");
//        sysPermission.setParentIds("0/");
//        sysPermission.setResourceType("menu");
////        sysPermission.setRoles(lstRole);
//        sysPermission.setUrl("userInfo/userList");
//        lstSysPermission.add(sysPermission);
//        sysRole.setPermissions(lstSysPermission);
//
//        userInfo.setRoleList(lstRole);
//
//
//        return userInfo;
//    }
}
