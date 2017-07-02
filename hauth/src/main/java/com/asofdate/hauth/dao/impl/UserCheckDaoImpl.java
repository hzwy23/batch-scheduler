package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.UserCheckDao;
import com.asofdate.hauth.entity.UserLoginEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/16.
 */
@Repository
public class UserCheckDaoImpl implements UserCheckDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findByUserId(String user_id) {
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_001, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                UserLoginEntity uc = new UserLoginEntity();
                uc.setUsername(resultSet.getString("user_id"));
                uc.setPassword(resultSet.getString("user_passwd"));
                uc.setStatusId(resultSet.getString("status_id"));
                uc.setContinueErrorCnt(resultSet.getInt("continue_error_cnt"));
                return uc;
            }
        }, user_id);
        return list;
    }
}
