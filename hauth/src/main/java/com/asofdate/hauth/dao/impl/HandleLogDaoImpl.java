package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.HandleLogDao;
import com.asofdate.hauth.entity.HandleLogEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Repository
public class HandleLogDaoImpl implements HandleLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HandleLogEntity> findAll(String domainId) {
        RowMapper<HandleLogEntity> rowMapper = new BeanPropertyRowMapper<>(HandleLogEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_012, rowMapper, domainId);
    }

    @Override
    public List<HandleLogEntity> findAll(String domainId, Integer offset, Integer limit) {
        RowMapper<HandleLogEntity> rowMapper = new BeanPropertyRowMapper<>(HandleLogEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_029, rowMapper, domainId, offset, limit);
    }

    @Override
    public Integer getTotal(String domainId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_030, Integer.class, domainId);
    }
}
