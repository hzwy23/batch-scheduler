package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.DomainDao;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Repository
public class DomainDaoImpl implements DomainDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DomainEntity> findAll() {
        RowMapper<DomainEntity> rowMapper = new BeanPropertyRowMapper<>(DomainEntity.class);
        List<DomainEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_118, rowMapper);
        return list;
    }

    @Override
    public List<DomainEntity> getAll() {
        RowMapper<DomainEntity> rowMapper = new BeanPropertyRowMapper<>(DomainEntity.class);
        List<DomainEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_025, rowMapper);
        return list;
    }

    @Override
    public int update(DomainEntity domainEntity) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_038,
                domainEntity.getDomainDesc(),
                domainEntity.getDomainStatusId(),
                domainEntity.getDomainModifyUser(),
                domainEntity.getDomainId());
    }

    @Transactional
    @Override
    public int delete(List<DomainEntity> list) {
        for (DomainEntity m : list) {
            jdbcTemplate.update(SqlDefine.sys_rdbms_037, m.getDomainId());
        }
        return 1;
    }

    @Override
    public int add(DomainEntity domainEntity) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_036,
                domainEntity.getDomainId(),
                domainEntity.getDomainDesc(),
                domainEntity.getDomainStatusId(),
                domainEntity.getCreateUserId(),
                domainEntity.getDomainModifyUser());
    }

    @Override
    public DomainEntity getDomainDetails(String domainId) {
        BeanPropertyRowMapper<DomainEntity> rowMapper = BeanPropertyRowMapper.newInstance(DomainEntity.class);
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_084,rowMapper,domainId);
    }
}
