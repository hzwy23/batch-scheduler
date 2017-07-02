package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.ResourceDao;
import com.asofdate.hauth.dao.RoleResourceDao;
import com.asofdate.hauth.entity.ResourceEntity;
import com.asofdate.hauth.entity.RoleResourceEntity;
import com.asofdate.sql.SqlDefine;
import com.asofdate.utils.JoinCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/20.
 */
@Repository
public class RoleResourceDaoImpl implements RoleResourceDao {
    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RoleResourceEntity> findAll(String roleId) {
        RowMapper<RoleResourceEntity> rowMapper = new BeanPropertyRowMapper<>(RoleResourceEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_209, rowMapper, roleId);
    }

    @Override
    public List<ResourceEntity> getOther(String roleId) {
        // 已经拥有的曲线列表
        Set<String> set = getAll(roleId);
        List<ResourceEntity> list = resourceDao.findAll();
        Set<String> unSet = new HashSet<>();
        Set<String> ret = new HashSet<>();

        // 获取没有得到的资源
        for (int i = 0; i < list.size(); i++) {
            String resId = list.get(i).getRes_id();
            if (!set.contains(resId)) {
                unSet.add(resId);
            }
        }

        for (String m : unSet) {
            getParent(list, m, ret);
        }

        for (int i = 0; i < list.size(); i++) {
            String resId = list.get(i).getRes_id();
            if (unSet.contains(resId)) {
                continue;
            }
            if (ret.contains(resId)) {
                continue;
            }
            list.remove(i);
            i--;
        }
        return list;
    }

    @Transactional
    @Override
    public int revoke(String roleId, String resId) {
        List<ResourceEntity> list = resourceDao.findAll();
        Set<String> child = new HashSet<>();
        getChild(list, resId, child);
        child.add(resId);

        for (String m : child) {
            jdbcTemplate.update(SqlDefine.sys_rdbms_093, roleId, m);
        }
        return 1;
    }

    @Transactional
    @Override
    public int auth(String roleId, String resId) {
        List<ResourceEntity> list = resourceDao.findAll();
        Set<String> newRes = new HashSet<>();

        Set<String> parent = new HashSet<>();
        Set<String> child = new HashSet<>();

        getParent(list, resId, parent);
        getChild(list, resId, child);
        child.add(resId);

        Set<String> owner = getAll(roleId);

        for (String m : parent) {
            if (!owner.contains(m)) {
                newRes.add(m);
            }
        }

        for (String m : child) {
            if (!owner.contains(m)) {
                newRes.add(m);
            }
        }

        for (String m : newRes) {
            String uuid = JoinCode.join(roleId, m);
            jdbcTemplate.update(SqlDefine.sys_rdbms_074, uuid, roleId, m);
        }
        return 1;
    }

    private void getParent(List<ResourceEntity> all, String resId, Set<String> set) {
        for (ResourceEntity m : all) {
            if (resId.equals(m.getRes_id())) {
                if (set.contains(m.getRes_up_id())) {
                    continue;
                }
                for (ResourceEntity c : all) {
                    if (m.getRes_up_id().equals(c.getRes_id())) {
                        set.add(m.getRes_up_id());
                        getParent(all, m.getRes_up_id(), set);
                        break;
                    }
                }
            }
        }
    }

    private void getChild(List<ResourceEntity> all, String resId, Set<String> set) {
        for (ResourceEntity m : all) {
            if (resId.equals(m.getRes_up_id())) {
                if (set.contains(m.getRes_id())) {
                    continue;
                }
                set.add(m.getRes_id());
                getChild(all, m.getRes_id(), set);
            }
        }
    }

    private Set<String> getAll(String roleId) {
        Set<String> set = new HashSet<>();
        jdbcTemplate.query(SqlDefine.sys_rdbms_100, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                set.add(resultSet.getString("res_id"));
            }
        }, roleId);
        return set;
    }
}
