package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.ArgumentDefineDao;
import com.asofdate.batch.entity.ArgumentDefineEntity;
import com.asofdate.batch.sql.SqlDefine;
import com.asofdate.utils.JoinCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
@Repository
public class ArgumentDefineDaoImpl implements ArgumentDefineDao {
    private final Logger logger = LoggerFactory.getLogger(ArgumentDefineDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId) {
        RowMapper<ArgumentDefineEntity> rowMapper = new BeanPropertyRowMapper<ArgumentDefineEntity>(ArgumentDefineEntity.class);
        List<ArgumentDefineEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_104, rowMapper, domainId);
        return list;
    }

    @Override
    public int add(ArgumentDefineEntity m) {
        String id = JoinCode.join(m.getDomainId(), m.getArgId());
        return jdbcTemplate.update(SqlDefine.sys_rdbms_119,
                id,
                m.getArgType(),
                m.getArgValue(),
                m.getArgId(),
                m.getCreateUser(),
                m.getModifyUser(),
                m.getDomainId(),
                m.getArgDesc(),
                m.getBindAsOfDate());
    }

    @Transactional
    @Override
    public String delete(List<ArgumentDefineEntity> m) {
        try {
            for (ArgumentDefineEntity l : m) {
                jdbcTemplate.update(SqlDefine.sys_rdbms_120, l.getArgId(), l.getDomainId());
            }
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public int update(ArgumentDefineEntity m) {
        String id = JoinCode.join(m.getDomainId(), m.getArgId());
        return jdbcTemplate.update(SqlDefine.sys_rdbms_121,
                m.getModifyUser(),
                m.getBindAsOfDate(),
                m.getArgDesc(),
                m.getArgValue(),
                id,
                m.getDomainId());

    }
}
