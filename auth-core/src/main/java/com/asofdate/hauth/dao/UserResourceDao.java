package com.asofdate.hauth.dao;

import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public interface UserResourceDao {
    Set<String> findAll(String userId);
}
