package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.utils.RetMsg;

/**
 * Created by hzwy23 on 2017/5/18.
 */
public interface UserDetailsService {
    UserDetailsEntity findById(String userId);
    RetMsg changeTheme(String themeId, String username);
    RetMsg changePassword(String newPasswd, String userId, String oldPasswd);
}
