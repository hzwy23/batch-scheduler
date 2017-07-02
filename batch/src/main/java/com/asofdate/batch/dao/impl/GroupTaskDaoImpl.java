package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupTaskDao;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.sql.SqlDefine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class GroupTaskDaoImpl implements GroupTaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<GroupTaskEntity>(GroupTaskEntity.class);
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_109, rowMapper, domainId);
        return list;
    }

    @Override
    public JSONArray getTask(String groupId) {
        JSONArray jsonArray = new JSONArray();
        jdbcTemplate.query(SqlDefine.sys_rdbms_133, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", resultSet.getString("id"));
                jsonObject.put("group_id", resultSet.getString("group_id"));
                jsonObject.put("task_id", resultSet.getString("task_id"));
                jsonObject.put("domain_id", resultSet.getString("domain_id"));
                jsonObject.put("task_desc", resultSet.getString("task_desc"));
                jsonObject.put("task_type", resultSet.getString("task_type"));
                jsonObject.put("task_type_desc", resultSet.getString("task_type_desc"));
                jsonObject.put("code_number", resultSet.getString("code_number"));
                jsonArray.put(jsonObject);
            }
        }, groupId);
        return jsonArray;
    }

    @Override
    public String getTaskId(String id) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_136, String.class, id);
    }

    @Override
    public int deleteTask(String id) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_147, id);
    }

    @Transactional
    @Override
    public int deleteTask(Set<String> args) {
        for (String id : args) {
            int size = jdbcTemplate.update(SqlDefine.sys_rdbms_147, id);
            if (size != 1) {
                return -1;
            }
        }
        return 1;
    }

    @Override
    public int addTask(String id, String groupId, String taskId, String domainId) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_148, id, groupId, taskId, domainId);
    }

    @Override
    public int addArg(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_149,
                    jsonObject.getString("id"),
                    jsonObject.getString("arg_id"),
                    jsonObject.getString("arg_value"),
                    jsonObject.getString("domain_id"))) {
                return 0;
            }
        }
        return 0;
    }
}
