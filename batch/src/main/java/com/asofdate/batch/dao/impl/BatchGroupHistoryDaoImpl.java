package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchGroupHistoryDao;
import com.asofdate.batch.entity.BatchGroupHistoryEntity;
import com.asofdate.sql.SqlDefine;
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
public class BatchGroupHistoryDaoImpl implements BatchGroupHistoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BatchGroupHistoryEntity> findAll(String uuid) {
        RowMapper<BatchGroupHistoryEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupHistoryEntity.class);
        List<BatchGroupHistoryEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_197, rowMapper, uuid);
        for (BatchGroupHistoryEntity bh : list) {
            String gid = bh.getGid();
            Integer totalCnt = getTotalJobs(uuid, gid);
            Integer completeCnt = getCompleteJobs(uuid, gid);
            bh.setTotalJobsCnt(totalCnt);
            bh.setCompleteJobsCnt(completeCnt);
        }
        return list;
    }

    private Integer getTotalJobs(String uuid, String gid) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_198, Integer.class, uuid, gid);
    }

    private Integer getCompleteJobs(String uuid, String gid) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_199, Integer.class, uuid, gid);
    }

}
