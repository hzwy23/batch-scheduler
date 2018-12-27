package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.SysConfigDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.entity.ProcEntity;
import com.asofdate.batch.entity.SysConfigEntity;
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
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<SysConfigEntity> findAll(String domainId) {
        RowMapper<SysConfigEntity> rowMapper = new BeanPropertyRowMapper<SysConfigEntity>(SysConfigEntity.class);
        List<SysConfigEntity> commonConfig = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_181"), rowMapper);
        List<SysConfigEntity> domainConfigList = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_187"), rowMapper, domainId);
        Map<String, SysConfigEntity> map = new HashMap<>();

        for (int i = 0; i < domainConfigList.size(); i++) {
            map.put(domainConfigList.get(i).getConfigId(), domainConfigList.get(i));
        }

        for (int i = 0; i < commonConfig.size(); i++) {
            String cid = commonConfig.get(i).getConfigId();
            if (map.containsKey(cid)) {
                commonConfig.get(i).setConfigValue(map.get(cid).getConfigValue());
            }
        }
        return commonConfig;
    }

    @Override
    public int setValue(String domainId, String configId, String configValue) {

        String uuid = JoinCode.join(domainId, configId);

        if (isExists(domainId, configId)) {
            return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_185"), configValue, uuid);
        }
        return jdbcTemplate.update(
                batchSqlText.getSql("sys_rdbms_186"),
                configId, configValue, domainId, uuid);
    }

    @Override
    public String getValue(String domainId, String configId) {
        String defaultValue = jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_182"), String.class, configId);
        try {
            String userValue = jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_183"), String.class, domainId, configId);
            return userValue.isEmpty() ? defaultValue : userValue;
        } catch (EmptyResultDataAccessException e) {
            return defaultValue;
        }
    }

    @Override
    public List<ProcEntity> getProcList() {
        RowMapper<ProcEntity> rowMapper = new BeanPropertyRowMapper<>(ProcEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_216"), rowMapper);
    }

    private boolean isExists(String domainId, String configId) {
        String uuid = JoinCode.join(domainId, configId);
        int cnt = jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_184"), Integer.class, uuid);
        return cnt == 1;
    }
}
