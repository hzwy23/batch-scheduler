package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchGroupDao;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.batch.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class BatchGroupDaoImpl implements BatchGroupDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId, String batchId) {
        RowMapper<BatchGroupEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_106, rowMapper, domainId, batchId);
    }

    @Override
    public List<BatchGroupEntity> getGroup(String batchId) {
        RowMapper<BatchGroupEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_137, rowMapper, batchId);
    }

    @Transactional
    @Override
    public int addGroup(List<BatchGroupEntity> list) {
        for (BatchGroupEntity m : list) {

            String id = UUID.randomUUID().toString();
            String batch_id = m.getBatchId();
            String group_id = m.getGroupId();
            String domain_id = m.getDomainId();

            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_154,
                    id, batch_id, group_id, domain_id)) {
                return -1;
            }
        }
        return 1;
    }

    @Transactional
    @Override
    public int deleteGroup(List<BatchGroupEntity> list) {
        for (BatchGroupEntity m : list) {
            String id = m.getSuiteKey();
            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_155, id)) {
                return -1;
            }
        }
        return 1;
    }

    @Override
    public List<BatchGroupEntity> getDependency(String batchid, String id) {
        List<BatchGroupEntity> list = getGroup(batchid);
        Set<String> set = getChildren(batchid, id);

        for (int i = 0; i < list.size(); i++) {
            String sub = list.get(i).getSuiteKey();
            if (set.contains(sub)) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    private Set<String> getChildren(String batchId, String id) {
        RowMapper<GroupDependencyEntity> rowMapper = new BeanPropertyRowMapper<>(GroupDependencyEntity.class);
        List<GroupDependencyEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_073, rowMapper, batchId);

        Set<String> set = new HashSet<>();
        children(list, id, set);
        set.add(id);

        for (BatchGroupEntity m : getOwner(id)) {
            set.add(m.getUpSuiteKey());
        }
        return set;
    }

    private List<BatchGroupEntity> getOwner(String id) {
        RowMapper<BatchGroupEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_138, rowMapper, id);
    }

    private void children(List<GroupDependencyEntity> all, String id, Set<String> set) {
        for (GroupDependencyEntity m : all) {
            String upId = m.getUpSuiteKey();
            if (upId == null || set.contains(m.getSuiteKey())) {
                continue;
            }
            if (id.equals(upId)) {
                set.add(m.getSuiteKey());
                children(all, m.getSuiteKey(), set);
            }
        }
    }

    @Override
    public List<BatchGroupEntity> getGroupDependency(String suiteKey) {
        RowMapper<BatchGroupEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_138, rowMapper, suiteKey);
    }

    @Override
    public int deleteGroupDependency(String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_153, uuid);
    }

    @Transactional
    @Override
    public int addGroupDependency(List<GroupDependencyEntity> list) {
        for (GroupDependencyEntity m : list) {

            String domainId = m.getDomainId();
            String id = m.getSuiteKey();
            String upId = m.getUpSuiteKey();
            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_156, id, upId, domainId)) {
                return -1;
            }
        }
        return 1;
    }

}
