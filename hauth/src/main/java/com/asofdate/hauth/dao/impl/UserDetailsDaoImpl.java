package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.UserDetailsDao;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.sql.SqlText;
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
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Override
    public List findById(String userId) {
        RowMapper<UserDetailsEntity> rowMapper = new BeanPropertyRowMapper<UserDetailsEntity>(UserDetailsEntity.class);
        List list = jdbcTemplate.query(sqlText.getSql("sys023"), rowMapper, userId);
        return list;
    }

    @Override
    public int changePasswd(String newPasswd, String userId, String oldPasswd) {
        return jdbcTemplate.update(sqlText.getSql("sys014"), newPasswd, userId, oldPasswd);
    }
}
