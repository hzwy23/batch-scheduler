package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchHistoryDao;
import com.asofdate.batch.entity.BatchHistoryEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
@Repository
public class BatchHistoryDaoImpl implements BatchHistoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BatchHistoryEntity> findAll(String domainId) {
        RowMapper<BatchHistoryEntity> rowMapper = new BeanPropertyRowMapper<>(BatchHistoryEntity.class);
        List<BatchHistoryEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_193, rowMapper, domainId);
        return list;
    }

    @Override
    public int delete(List<BatchHistoryEntity> list) {
        for (BatchHistoryEntity m : list) {
            jdbcTemplate.update(SqlDefine.sys_rdbms_194, m.getUuid());
        }
        return 1;
    }
}
