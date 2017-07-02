package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.sql.SqlDefine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class TaskArgumentDaoImpl implements TaskArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId) {
        RowMapper<TaskArgumentEntity> rowMapper = new BeanPropertyRowMapper<TaskArgumentEntity>(TaskArgumentEntity.class);
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_110, rowMapper, domainId);
        return list;
    }

    @Override
    public JSONArray getTaskArg(String taskId) {
        JSONArray jsonArray = new JSONArray();
        jdbcTemplate.query(SqlDefine.sys_rdbms_132, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uuid", resultSet.getString("uuid"));
                jsonObject.put("task_id", resultSet.getString("task_id"));
                jsonObject.put("arg_id", resultSet.getString("arg_id"));
                switch (resultSet.getString("arg_type")) {
                    case "1":
                        jsonObject.put("arg_value", resultSet.getString("fixed_arg_value"));
                        break;
                    case "2":
                        jsonObject.put("arg_value", resultSet.getString("arg_value"));
                        break;
                    default:
                        jsonObject.put("arg_value", "-");
                }

                jsonObject.put("sort_id", resultSet.getString("sort_id"));
                jsonObject.put("domain_id", resultSet.getString("domain_id"));
                jsonObject.put("arg_type", resultSet.getString("arg_type"));
                jsonObject.put("arg_type_desc", resultSet.getString("arg_type_desc"));
                jsonObject.put("arg_desc", resultSet.getString("arg_desc"));
                jsonObject.put("code_number", resultSet.getString("code_number"));
                jsonArray.put(jsonObject);
            }
        }, taskId);
        return jsonArray;
    }

    @Override
    public int updateSort(String sortId, String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_141, sortId, uuid);
    }

    @Override
    public int deleteArg(String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_142, uuid);
    }

    @Override
    public JSONObject getArgType(String argId) {
        JSONObject jsonObject = new JSONObject();
        jdbcTemplate.query(SqlDefine.sys_rdbms_143, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                jsonObject.put("arg_id", resultSet.getString("arg_id"));
                jsonObject.put("arg_desc", resultSet.getString("arg_desc"));
                jsonObject.put("arg_value", resultSet.getString("arg_value"));
                jsonObject.put("arg_type", resultSet.getString("arg_type"));
                jsonObject.put("domain_id", resultSet.getString("domain_id"));
            }
        }, argId);
        return jsonObject;
    }

    @Override
    public int addArg(JSONObject jsonObject) {
        String taskId = jsonObject.getString("task_id");
        String argId = jsonObject.getString("arg_id");
        String domainId = jsonObject.getString("domain_id");
        String argValue = jsonObject.getString("arg_value");
        String sortId = jsonObject.getString("sort_id");
        return jdbcTemplate.update(SqlDefine.sys_rdbms_144, taskId, argId, domainId, argValue, sortId);
    }

    @Override
    public int updateArgValue(String argValue, String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_145, argValue, uuid);
    }
}
