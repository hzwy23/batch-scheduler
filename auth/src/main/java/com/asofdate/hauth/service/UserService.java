package com.asofdate.hauth.service;

import com.asofdate.hauth.dto.UserDTO;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.entity.UserEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface UserService {
    List<UserEntity> findAll();

    List<UserEntity> findAll(String orgId, String statusCd);

    int add(UserEntity userEntity);

    int delete(List<UserEntity> list);

    int update(UserEntity userEntity);

    int changePassword(UserDTO m);

    int changeStatus(String userId, String status);

    UserDetailsEntity findById(String userId);

    RetMsg changeTheme(String themeId, String username);

    RetMsg changePassword(String newPasswd, String userId, String oldPasswd);
}
