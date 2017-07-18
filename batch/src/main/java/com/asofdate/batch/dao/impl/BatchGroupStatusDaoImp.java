package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchGroupStatusDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/15.
 */
@Repository
public class BatchGroupStatusDaoImp implements BatchGroupStatusDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int init(BatchRunConfDto conf, Map<String, Integer> map) {

        // 删除当前批次的任务组历史信息
        jdbcTemplate.update(SqlDefine.sys_rdbms_172, conf.getBatchId(), conf.getAsOfDate());

        // 初始化批次运行中需要的任务组状态信息
        for (Map.Entry<String, Integer> gid : map.entrySet()) {
            jdbcTemplate.update(SqlDefine.sys_rdbms_173, conf.getBatchId(), gid.getKey(), gid.getValue(), conf.getAsOfDate());
        }
        return 1;
    }

    @Override
    public int setSuiteKeyStatus(BatchRunConfDto conf, String suiteKey, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_174, status, conf.getBatchId(), suiteKey, conf.getAsOfDate());
    }

    @Override
    public int getSuiteKeyStatus(BatchRunConfDto conf, String suiteKey) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_175, Integer.class, conf.getBatchId(), suiteKey, conf.getAsOfDate());
    }

    @Override
    public int getCompletedCnt(BatchRunConfDto conf) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_177, Integer.class, conf.getBatchId(), conf.getAsOfDate());
    }

    @Override
    public int getTotalCnt(BatchRunConfDto conf) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_177, Integer.class, conf.getBatchId(), conf.getAsOfDate());
    }

    @Override
    public int setGroupRunning(BatchRunConfDto conf, String suiteKey, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_188, status, suiteKey, conf.getBatchId(), conf.getAsOfDate());
    }

    @Override
    public int setGroupEnd(BatchRunConfDto conf, String suiteKey, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_189, status, suiteKey, conf.getBatchId(), conf.getAsOfDate());
    }
}
