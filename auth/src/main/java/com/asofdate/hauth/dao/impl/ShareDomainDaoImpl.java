package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.DomainDao;
import com.asofdate.hauth.dao.ShareDomainDao;
import com.asofdate.hauth.entity.DomainShareEntity;
import com.asofdate.hauth.entity.ShareDomainEntity;
import com.asofdate.hauth.sql.SqlText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/19.
 */
@Repository
public class ShareDomainDaoImpl implements ShareDomainDao {
    @Autowired
    private DomainDao domainDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Override
    public List<ShareDomainEntity> findAll(String domainId) {
        RowMapper<ShareDomainEntity> rowMapper = new BeanPropertyRowMapper<>(ShareDomainEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys083"), rowMapper, domainId);
    }

    @Override
    public List<ShareDomainEntity> unShareTarget(String domainId) {
        RowMapper<ShareDomainEntity> rowMapper = new BeanPropertyRowMapper<>(ShareDomainEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys085"), rowMapper, domainId, domainId);
    }

    @Override
    public int add(ShareDomainEntity shareDomainEntity) {
        return jdbcTemplate.update(sqlText.getSql("sys086"),
                shareDomainEntity.getDomain_id(),
                shareDomainEntity.getTarget_domain_id(),
                shareDomainEntity.getAuthorization_level(),
                shareDomainEntity.getCreate_user(),
                shareDomainEntity.getModify_user());
    }

    @Transactional
    @Override
    public int delete(List<ShareDomainEntity> list) {
        for (ShareDomainEntity m : list) {
            jdbcTemplate.update(sqlText.getSql("sys087"), m.getUuid());
        }
        return 1;
    }

    @Override
    public int update(ShareDomainEntity shareDomainEntity) {
        return jdbcTemplate.update(sqlText.getSql("sys088"),
                shareDomainEntity.getAuthorization_level(),
                shareDomainEntity.getModify_user(),
                shareDomainEntity.getUuid());
    }

    @Override
    public int getAuthLevel(String domainId, String userDomainId) {
        return jdbcTemplate.queryForObject(sqlText.getSql("sys010"), Integer.class, domainId, userDomainId);
    }

    @Override
    public List<DomainShareEntity> findShareTo(String domainId) {
        RowMapper<DomainShareEntity> rowMapper = new BeanPropertyRowMapper<>(DomainShareEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys117"), rowMapper, domainId);
    }

    @Override
    public Set<String> findShareDomain(String userId) {
        RowMapper<DomainShareEntity> rowMapper = new BeanPropertyRowMapper<>(DomainShareEntity.class);
        List<DomainShareEntity> list = jdbcTemplate.query(sqlText.getSql("sys116"), rowMapper, userId);

        Set<String> set = new HashSet<>();
        for (DomainShareEntity m : list) {
            set.add(m.getDomain_id());
        }
        return set;
    }
}
