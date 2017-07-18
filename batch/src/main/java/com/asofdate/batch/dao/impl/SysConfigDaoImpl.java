package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.SysConfigDao;
import com.asofdate.batch.entity.SysConfigEntity;
import com.asofdate.batch.sql.SqlDefine;
import com.asofdate.utils.JoinCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/15.
 */
@Repository
public class SysConfigDaoImpl implements SysConfigDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SysConfigEntity> findAll(String domainId) {
        RowMapper<SysConfigEntity> rowMapper = new BeanPropertyRowMapper<SysConfigEntity>(SysConfigEntity.class);
        List<SysConfigEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_181, rowMapper);
        List<SysConfigEntity> list2 = jdbcTemplate.query(SqlDefine.sys_rdbms_187, rowMapper, domainId);
        Map<String, SysConfigEntity> map = new HashMap<>();

        for (int i = 0; i < list2.size(); i++) {
            map.put(list2.get(i).getConfigId(), list2.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            String cid = list.get(i).getConfigId();
            if (map.containsKey(cid)) {
                list.get(i).setConfigValue(map.get(cid).getConfigValue());
            }
        }
        return list;
    }

    @Override
    public int setValue(String domainId, String configId, String configValue) {
        String uuid = JoinCode.join(domainId, configId);
        if (isExists(domainId, configId)) {
            return jdbcTemplate.update(SqlDefine.sys_rdbms_185, configValue, uuid);
        }
        return jdbcTemplate.update(SqlDefine.sys_rdbms_186, configId, configValue, domainId, uuid);
    }

    @Override
    public String getValue(String domainId, String configId) {
        String defaultValue = jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_182, String.class, configId);
        try {
            String userValue = jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_183, String.class, domainId, configId);
            return userValue.isEmpty() ? defaultValue : userValue;
        } catch (EmptyResultDataAccessException e) {
            return defaultValue;
        }
    }

    private boolean isExists(String domainId, String configId) {
        String uuid = JoinCode.join(domainId, configId);
        int cnt = jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_184, Integer.class, uuid);
        return cnt == 1;
    }
}
