package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.OrgDao;
import com.asofdate.hauth.entity.OrgEntity;
import com.asofdate.hauth.sql.SqlText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Repository
public class OrgDaoImpl implements OrgDao {
    private final Logger logger = LoggerFactory.getLogger(OrgDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Override
    public List<OrgEntity> findAll(String domainId) {
        RowMapper<OrgEntity> rowMapper = new BeanPropertyRowMapper<>(OrgEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys041"), rowMapper, domainId);
    }

    @Override
    public List<OrgEntity> findSub(String domainId, String orgId) {
        List<OrgEntity> list = findAll(domainId);
        List<OrgEntity> ret = new ArrayList<OrgEntity>();
        for (OrgEntity m : list) {
            if (orgId.equals(m.getOrg_id())) {
                ret.add(m);
                break;
            }
        }
        getSub(list, orgId, ret);
        return ret;
    }

    @Override
    public int add(OrgEntity orgEntity) {
        return jdbcTemplate.update(sqlText.getSql("sys043"),
                orgEntity.getCode_number(),
                orgEntity.getOrg_desc(),
                orgEntity.getUp_org_id(),
                orgEntity.getDomain_id(),
                orgEntity.getCreate_user(),
                orgEntity.getModify_user(),
                orgEntity.getOrg_id());
    }

    @Transactional
    @Override
    public int delete(List<OrgEntity> list) {
        for (OrgEntity m : list) {
            jdbcTemplate.update(sqlText.getSql("sys044"), m.getOrg_id(), m.getDomain_id());
        }
        return 1;
    }

    @Override
    public int update(OrgEntity orgEntity) {
        logger.debug("{},{},{},{}", orgEntity.getOrg_desc(), orgEntity.getUp_org_id(), orgEntity.getModify_user(), orgEntity.getOrg_id());
        return jdbcTemplate.update(sqlText.getSql("sys069"),
                orgEntity.getOrg_desc(),
                orgEntity.getUp_org_id(),
                orgEntity.getModify_user(),
                orgEntity.getOrg_id());
    }

    private void getSub(List<OrgEntity> all, String orgId, List<OrgEntity> ret) {
        for (OrgEntity a : all) {
            if (orgId.equals(a.getUp_org_id())) {
                if (ret.contains(a)) {
                    continue;
                }
                ret.add(a);
                getSub(all, a.getOrg_id(), ret);
            }
        }
    }
}
