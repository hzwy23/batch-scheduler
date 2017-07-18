package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.TaskDefineDao;
import com.asofdate.batch.entity.TaskDefineEntity;
import com.asofdate.batch.sql.SqlDefine;
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
public class TaskDefineDaoImpl implements TaskDefineDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId) {
        RowMapper<TaskDefineEntity> rowMapper = new BeanPropertyRowMapper<TaskDefineEntity>(TaskDefineEntity.class);
        List<TaskDefineEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_111, rowMapper, domainId);
        return list;
    }

    @Override
    public int add(TaskDefineEntity m) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_125,
                m.getTaskId(),
                m.getCodeNumber(),
                m.getTaskDesc(),
                m.getTaskType(),
                m.getCreateUser(),
                m.getModifyUser(),
                m.getDomainId(),
                m.getScriptFile());
    }

    @Override
    public String delete(List<TaskDefineEntity> m) {
        for (TaskDefineEntity l : m) {
            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_127, l.getTaskId(), l.getDomainId())) {
                return "删除[" + l.getCodeNumber() + "]失败";
            }
        }
        return "success";
    }

    @Override
    public int update(TaskDefineEntity m) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_126,
                m.getTaskDesc(),
                m.getTaskType(),
                m.getScriptFile(),
                m.getTaskId(),
                m.getDomainId());
    }
}
