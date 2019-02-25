package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.HandleLogDao;
import com.asofdate.hauth.entity.HandleLogEntity;
import com.asofdate.hauth.sql.SqlText;
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
    @Autowired
    private SqlText sqlText;

    @Override
    public List<HandleLogEntity> findAll() {
        RowMapper<HandleLogEntity> rowMapper = new BeanPropertyRowMapper<>(HandleLogEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys012"), rowMapper);
    }

    @Override
    public List<HandleLogEntity> findAll(Integer offset, Integer limit) {
        RowMapper<HandleLogEntity> rowMapper = new BeanPropertyRowMapper<>(HandleLogEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys029"), rowMapper, offset, limit);
    }

    @Override
    public Integer getTotal() {
        return jdbcTemplate.queryForObject(sqlText.getSql("sys030"), Integer.class);
    }
}
