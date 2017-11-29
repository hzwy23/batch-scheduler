package com.asofdate.hauth.dao;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/16.
 */
public interface UserCheckDao {
    List findByUserId(String user_id);
}
