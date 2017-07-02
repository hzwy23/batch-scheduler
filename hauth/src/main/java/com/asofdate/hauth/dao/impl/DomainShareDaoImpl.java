package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.DomainDao;
import com.asofdate.hauth.dao.DomainShareDao;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.entity.DomainShareEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Repository
public class DomainShareDaoImpl implements DomainShareDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DomainDao domainDao;

    @Override
    public List<DomainShareEntity> findAuth(String targetDomainID) {
        RowMapper<DomainShareEntity> rowMapper = new BeanPropertyRowMapper<>(DomainShareEntity.class);
        List<DomainShareEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_116, rowMapper, targetDomainID);
        return list;
    }

    @Override
    public List<DomainEntity> findUnshareTo(String domainId) {
        List<DomainEntity> list = domainDao.findAll();
        List<DomainShareEntity> dslist = findShareTo(domainId);
        Set<String> set = new HashSet<>();
        for (DomainShareEntity m : dslist) {
            set.add(m.getTarget_domain_id());
        }
        for (int i = 0; i < list.size(); i++) {
            if (set.contains(list.get(i).getDomain_id())) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Override
    public List<DomainShareEntity> findShareTo(String domainId) {
        RowMapper<DomainShareEntity> rowMapper = new BeanPropertyRowMapper<>(DomainShareEntity.class);
        List<DomainShareEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_117, rowMapper, domainId);
        return list;
    }

    @Override
    public Set<String> findAll(String targetDomainId) {
        RowMapper<DomainShareEntity> rowMapper = new BeanPropertyRowMapper<>(DomainShareEntity.class);
        List<DomainShareEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_116, rowMapper, targetDomainId);

        Set<String> set = new HashSet<>();
        set.add(targetDomainId);
        for (DomainShareEntity m : list) {
            set.add(m.getDomain_id());
        }
        return set;
    }

}
