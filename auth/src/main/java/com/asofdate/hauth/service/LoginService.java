package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.UserLoginEntity;

/**
 * Created by hzwy23 on 2017/5/16.
 */
public interface LoginService {
    UserLoginEntity loginValidator(String user_id, String password);

    UserLoginEntity findByUserId(String userId);
}
