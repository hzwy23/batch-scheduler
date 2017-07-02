package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.UserDetailsDao;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/18.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserDetailsDao userDetailsDao;

    @Override
    public UserDetailsEntity findById(String userId) {
        List<UserDetailsEntity> list = userDetailsDao.findById(userId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
