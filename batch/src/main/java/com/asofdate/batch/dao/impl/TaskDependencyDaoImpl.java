package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.TaskDependencyDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.dto.JobKeyDepDto;
import com.asofdate.batch.entity.TaskDependencyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Repository
public class TaskDependencyDaoImpl implements TaskDependencyDao {
    private final Logger logger = LoggerFactory.getLogger(TaskDependencyDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<TaskDependencyEntity> findAll(String domainId) {
        RowMapper<TaskDependencyEntity> rowMapper = new BeanPropertyRowMapper<TaskDependencyEntity>(TaskDependencyEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_113"), rowMapper, domainId);
    }

    @Override
    public List<JobKeyDepDto> findById(String domainId, String batchId) {
        RowMapper<JobKeyDepDto> rowMapper = new BeanPropertyRowMapper<JobKeyDepDto>(JobKeyDepDto.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_215"), rowMapper, domainId, batchId);
    }
}
