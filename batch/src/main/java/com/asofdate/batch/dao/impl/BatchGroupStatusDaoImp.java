package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchGroupStatusDao;
import com.asofdate.sql.SqlDefine;
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
    public int init(String batchId, Map<String, Integer> map) {
        // 删除当前批次的任务组历史信息
        jdbcTemplate.update(SqlDefine.sys_rdbms_172, batchId);
        for (Map.Entry<String, Integer> gid : map.entrySet()) {
            jdbcTemplate.update(SqlDefine.sys_rdbms_173, batchId, gid.getKey(), gid.getValue());
        }
        return 1;
    }

    @Override
    public int setGidStatus(String batchId, String gid, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_174, status, batchId, gid);
    }

    @Override
    public int getGidStatus(String batchId, String gid) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_175, Integer.class, batchId, gid);
    }

    @Override
    public int getCompletedCnt(String batchId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_177, Integer.class, batchId);
    }

    @Override
    public int getTotalCnt(String batchId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_177, Integer.class, batchId);
    }

    @Override
    public int setGroupRunning(String batchId, String gid, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_188, status, gid, batchId);
    }

    @Override
    public int setGroupEnd(String batchId, String gid, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_189, status, gid, batchId);
    }
}
