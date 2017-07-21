package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.entity.TaskArgumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class TaskArgumentDaoImpl implements TaskArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<TaskArgumentEntity> rowMapper = new BeanPropertyRowMapper<TaskArgumentEntity>(TaskArgumentEntity.class);
        List list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_110"), rowMapper, domainId);
        return list;
    }

    @Override
    public List<TaskArgumentEntity> getTaskArg(String taskId) {
        RowMapper<TaskArgumentEntity> rowMapper = new BeanPropertyRowMapper<>(TaskArgumentEntity.class);
        List<TaskArgumentEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_132"), rowMapper, taskId);
        for (TaskArgumentEntity m : list) {
            String typ = m.getArgType();
            if ("1".equals(typ)) {
                m.setArgValue(m.getFixedArgValue());
            } else if ("2".equals(typ)) {
                continue;
            } else {
                m.setArgValue("-");
            }
        }
        return list;
    }

    @Override
    public int updateSort(String sortId, String uuid) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_141"), sortId, uuid);
    }

    @Override
    public int deleteArg(String uuid) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_142"), uuid);
    }

    @Override
    public TaskArgumentEntity getArgType(String argId) {
        RowMapper<TaskArgumentEntity> rowMapper = BeanPropertyRowMapper.newInstance(TaskArgumentEntity.class);
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_143"), rowMapper, argId);
    }

    @Override
    public int addArg(TaskArgumentEntity taskArgumentEntity) {
        String taskId = taskArgumentEntity.getTaskId();
        String argId = taskArgumentEntity.getArgId();
        String domainId = taskArgumentEntity.getDomainId();
        String argValue = taskArgumentEntity.getArgValue();
        String sortId = taskArgumentEntity.getSortId();
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_144"), taskId, argId, domainId, argValue, sortId);
    }

    @Override
    public int updateArgValue(String argValue, String uuid) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_145"), argValue, uuid);
    }
}
