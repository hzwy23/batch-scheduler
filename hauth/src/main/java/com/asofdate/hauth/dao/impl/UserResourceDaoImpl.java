package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.UserResourceDao;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Repository
public class UserResourceDaoImpl implements UserResourceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Set<String> findAll(String userId) {
        Set<String> set = new HashSet<>();
        jdbcTemplate.query(SqlDefine.sys_rdbms_115, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                set.add(resultSet.getString("res_id"));
            }
        }, userId);
        return set;
    }
}
