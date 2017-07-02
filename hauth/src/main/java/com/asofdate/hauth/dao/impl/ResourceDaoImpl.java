package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.ResourceDao;
import com.asofdate.hauth.entity.ResourceEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/17.
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public List findAll() {
        RowMapper<ResourceEntity> rowMapper = new BeanPropertyRowMapper<ResourceEntity>(ResourceEntity.class);
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_071, rowMapper);
        return list;
    }

    @Override
    public List findSubByUpId(String resUpId) {
        List<ResourceEntity> list = findAll();
        List<ResourceEntity> ret = new ArrayList<>();
        dfs(list, resUpId, ret);
        return ret;
    }

    private void dfs(List<ResourceEntity> all, String resUpId, List<ResourceEntity> ret) {
        for (ResourceEntity m : all) {
            String upId = m.getRes_up_id();
            if (upId != null) {
                if (upId.equals(resUpId)) {
                    ret.add(m);
                    dfs(all, m.getRes_id(), ret);
                }
            }
        }
    }
}
