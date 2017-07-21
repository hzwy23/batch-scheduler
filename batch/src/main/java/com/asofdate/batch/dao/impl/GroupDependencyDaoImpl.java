package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupDependencyDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.entity.GroupDependencyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Repository
public class GroupDependencyDaoImpl implements GroupDependencyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<GroupDependencyEntity> findById(String domainId, String batchId) {
        RowMapper<GroupDependencyEntity> rowMapper = new BeanPropertyRowMapper<GroupDependencyEntity>(GroupDependencyEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_112"), rowMapper, domainId, batchId);
    }
}
