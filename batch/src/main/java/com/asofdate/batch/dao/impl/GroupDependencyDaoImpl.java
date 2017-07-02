package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupDependencyDao;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.sql.SqlDefine;
import org.json.JSONArray;
import org.json.JSONObject;
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

    @Override
    public List<GroupDependencyEntity> findAll(String domainId) {
        RowMapper<GroupDependencyEntity> rowMapper = new BeanPropertyRowMapper<GroupDependencyEntity>(GroupDependencyEntity.class);
        List<GroupDependencyEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_112, rowMapper, domainId);
        return list;
    }

    @Override
    public List<GroupDependencyEntity> findById(String domainId, String batchId) {
        List<GroupDependencyEntity> list = findAll(domainId);
        for (int i = 0; i < list.size(); i++) {
            if (batchId.equals(list.get(i))) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Override
    public List<BatchGroupEntity> getGroupDependency(String id) {
        RowMapper<BatchGroupEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_138, rowMapper, id);
    }

    @Override
    public int deleteGroupDependency(String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_153, uuid);
    }

    @Override
    public int addGroupDependency(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String domainId = jsonObject.getString("domain_id");
            String id = jsonObject.getString("id");
            String upId = jsonObject.getString("up_id");
            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_156, id, upId, domainId)) {
                return -1;
            }
        }
        return 1;
    }

}
