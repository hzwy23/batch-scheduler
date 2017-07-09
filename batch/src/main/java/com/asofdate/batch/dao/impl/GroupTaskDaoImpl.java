package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupTaskDao;
import com.asofdate.batch.dto.GroupDefineDto;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public List<GroupTaskEntity> getTask(String groupId) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<>(GroupTaskEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_133, rowMapper, groupId);
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

    @Transactional
    @Override
    public int addArg(List<GroupDefineDto> list) {
        for (GroupDefineDto m : list) {
            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_149,
                    m.getId(),
                    m.getArgId(),
                    m.getArgValue(),
                    m.getDomainId())) {
                return 0;
            }
        }
        return 1;
    }
}
