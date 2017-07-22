package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.ExecDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/13.
 */
@Repository
public class ExecDaoImpl implements ExecDao {
    private final Logger logger = LoggerFactory.getLogger(ExecDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public int insert(ExecLogEntity row) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_211"),
                row.getJobId(), row.getMessage(),
                row.getExecTime(), row.getSortId(),
                row.getBatchId(), row.getAsOfDate());
    }

    @Override
    public List<ExecLogEntity> query(String id, String jobId) {
        RowMapper<ExecLogEntity> rowMapper = new BeanPropertyRowMapper<>(ExecLogEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_214"), rowMapper, jobId, id);
    }

    @Override
    public void init(BatchRunConfDto conf) {
        jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_213"), conf.getBatchId(), conf.getAsOfDate());
    }
}
