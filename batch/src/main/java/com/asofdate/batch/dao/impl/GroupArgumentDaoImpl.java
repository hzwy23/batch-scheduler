package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupArgumentDao;
import com.asofdate.batch.entity.GroupArgumentEntity;
import com.asofdate.batch.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/30.
 */
@Repository
public class GroupArgumentDaoImpl implements GroupArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroupArgumentEntity> findAll(String domainId) {
        RowMapper<GroupArgumentEntity> rowMapper = new BeanPropertyRowMapper<>(GroupArgumentEntity.class);
        List<GroupArgumentEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_114, rowMapper, domainId);
        return list;
    }

    @Override
    public List<GroupArgumentEntity> getGroupArg(String id) {
        RowMapper<GroupArgumentEntity> rowMapper = new BeanPropertyRowMapper<>(GroupArgumentEntity.class);

        return jdbcTemplate.query(SqlDefine.sys_rdbms_135, rowMapper, id);
    }

    @Override
    public int updateArg(String argValue, String uuid, String argId) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_146, argValue, uuid, argId);
    }
}
