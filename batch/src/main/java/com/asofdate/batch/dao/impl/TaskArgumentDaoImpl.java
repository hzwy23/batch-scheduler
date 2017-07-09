package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.sql.SqlDefine;
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
    public List<TaskArgumentEntity> getTaskArg(String taskId) {
        RowMapper<TaskArgumentEntity> rowMapper = new BeanPropertyRowMapper<>(TaskArgumentEntity.class);
        List<TaskArgumentEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_132, rowMapper, taskId);
        for (TaskArgumentEntity m : list) {
            switch (m.getArgType()) {
                case "1":
                    m.setArgValue(m.getFixedArgValue());
                    break;
                case "2":
                    break;
                default:
                    m.setArgValue("-");
            }
        }
        return list;
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
    public TaskArgumentEntity getArgType(String argId) {
        TaskArgumentEntity taskArgumentEntity = new TaskArgumentEntity();

        jdbcTemplate.query(SqlDefine.sys_rdbms_143, new RowCallbackHandler() {

            @Override
            public void processRow(ResultSet arg0) throws SQLException {

                String arg_id = arg0.getString("arg_id");
                String arg_desc = arg0.getString("arg_desc");
                String arg_value = arg0.getString("arg_value");
                String arg_type = arg0.getString("arg_type");
                String domain_id = arg0.getString("domain_id");
                taskArgumentEntity.setArgId(arg_id);
                taskArgumentEntity.setArgDesc(arg_desc);
                taskArgumentEntity.setArgValue(arg_value);
                taskArgumentEntity.setArgType(arg_type);
                taskArgumentEntity.setDomainId(domain_id);
            }

        }, argId);
        return taskArgumentEntity;
    }

    @Override
    public int addArg(TaskArgumentEntity taskArgumentEntity) {
        String taskId = taskArgumentEntity.getTaskId();
        String argId = taskArgumentEntity.getArgId();
        String domainId = taskArgumentEntity.getDomainId();
        String argValue = taskArgumentEntity.getArgValue();
        String sortId = taskArgumentEntity.getSortId();
        return jdbcTemplate.update(SqlDefine.sys_rdbms_144, taskId, argId, domainId, argValue, sortId);
    }

    @Override
    public int updateArgValue(String argValue, String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_145, argValue, uuid);
    }
}
