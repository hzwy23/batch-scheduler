package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchJobRunningDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.entity.BatchJobStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Repository
public class BatchJobRunningDaoImpl implements BatchJobRunningDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<BatchJobStatusEntity> findAll(String batchId, String suiteKey, String asOfDate) {
        RowMapper<BatchJobStatusEntity> rowMapper = new BeanPropertyRowMapper<>(BatchJobStatusEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_204"), rowMapper, batchId, suiteKey, asOfDate);
    }

    @Override
    public BatchJobStatusEntity getDetails(String batchId, String gid, String tid) {
        RowMapper<BatchJobStatusEntity> rowMapper = new BeanPropertyRowMapper<>(BatchJobStatusEntity.class);
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_206"), rowMapper, batchId, gid, tid);
    }
}
