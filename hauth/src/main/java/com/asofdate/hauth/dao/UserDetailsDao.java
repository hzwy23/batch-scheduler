package com.asofdate.hauth.dao;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/18.
 */
public interface UserDetailsDao {
    List findById(String userId);
}
