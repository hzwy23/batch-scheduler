package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.UserResourceDao;
import com.asofdate.utils.Validator;
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


    private static final String SQL_TEXT = "select distinct r.res_id from sys_role_user t inner join sys_role_resource r on t.role_id = r.role_id where t.user_id = ?";

    private static final String SQL_QUERY_ALL = "select distinct r.res_id from sys_resource_info r";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Set<String> findAll(String userId) {

        Set<String> set = new HashSet<>();

        if (Validator.isAdmin(userId)) {
            jdbcTemplate.query(SQL_QUERY_ALL, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    set.add(resultSet.getString("res_id"));
                }
            });
        } else {
            jdbcTemplate.query(SQL_TEXT, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    set.add(resultSet.getString("res_id"));
                }
            }, userId);
        }

        return set;
    }
}
