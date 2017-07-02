package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchJobStatusDao;
import com.asofdate.sql.SqlDefine;
import com.asofdate.utils.JoinCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/14.
 */
@Repository
public class BatchJobStatusDaoImpl implements BatchJobStatusDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int init(String batchId, Map<String, Integer> map) {
        jdbcTemplate.update(SqlDefine.sys_rdbms_166, batchId);
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            String gid = JoinCode.getFirst(m.getKey());
            String tid = JoinCode.getLast(m.getKey());
            jdbcTemplate.update(SqlDefine.sys_rdbms_167, batchId, m.getKey(), m.getValue(), gid, tid);
        }
        return 1;
    }

    @Override
    public int setJobStatus(String batchId, String jobId, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_168, status, batchId, jobId);
    }

    @Override
    public int getJobStatus(String batchId, String jobId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_169, Integer.class, batchId, jobId);
    }

    @Override
    public int getCompletedCnt(String batchId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_171, Integer.class, batchId);
    }

    @Override
    public int getTotalCnt(String batchId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_170, Integer.class, batchId);
    }

    @Override
    public int setJobRunning(String batchId, String jobId, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_190, status, batchId, jobId);
    }

    @Override
    public int setJobEnd(String batchId, String jobId, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_191, status, batchId, jobId);
    }
}
