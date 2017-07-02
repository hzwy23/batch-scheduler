package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.UserDetailsEntity;

/**
 * Created by hzwy23 on 2017/5/18.
 */
public interface UserDetailsService {
    UserDetailsEntity findById(String userId);
}
