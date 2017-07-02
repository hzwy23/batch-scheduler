package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.UserDetailsDao;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/18.
 */
@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public List findById(String userId) {
        RowMapper<UserDetailsEntity> rowMapper = new BeanPropertyRowMapper<UserDetailsEntity>(UserDetailsEntity.class);
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_023, rowMapper, userId);
        return list;
    }
}
