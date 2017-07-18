package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupDefineDao;
import com.asofdate.batch.entity.GroupDefineEntity;
import com.asofdate.batch.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class GroupDefineDaoImpl implements GroupDefineDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId) {
        RowMapper<GroupDefineEntity> rowMapper = new BeanPropertyRowMapper<GroupDefineEntity>(GroupDefineEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_108, rowMapper, domainId);
    }

    @Override
    public int add(GroupDefineEntity m) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_124,
                m.getGroupId(),
                m.getCodeNumber(),
                m.getGroupDesc(),
                m.getCreateUser(),
                m.getModifyUser(),
                m.getDomainId());
    }

    @Override
    public String delete(List<GroupDefineEntity> m) {
        for (GroupDefineEntity l : m) {
            int i = jdbcTemplate.update(SqlDefine.sys_rdbms_122, l.getGroupId(), l.getDomainId());
            if (i == 0) {
                return "删除[" + l.getCodeNumber() + "]失败,任务组已经被引用,请先解除引用.";
            }
        }
        return "success";
    }

    @Override
    public int update(GroupDefineEntity m) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_123,
                m.getGroupDesc(),
                m.getGroupId(),
                m.getDomainId());
    }
}
