package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.RoleDao;
import com.asofdate.hauth.entity.RoleEntity;
import com.asofdate.hauth.entity.UserRoleEntity;
import com.asofdate.hauth.sql.SqlText;
import com.asofdate.utils.JoinCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Repository
public class RoleDaoImpl implements RoleDao {
    private final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Override
    public List<RoleEntity> findAll(String domainId) {
        RowMapper<RoleEntity> rowMapper = new BeanPropertyRowMapper<>(RoleEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys028"), rowMapper, domainId);
    }

    @Override
    public RoleEntity getDetails(String roleId) {
        RowMapper<RoleEntity> rowMapper = BeanPropertyRowMapper.newInstance(RoleEntity.class);
        return jdbcTemplate.queryForObject(sqlText.getSql("sys208"), rowMapper, roleId);
    }

    @Override
    public List<RoleEntity> getOther(String userId) {
        RowMapper<RoleEntity> rowMapper = new BeanPropertyRowMapper<>(RoleEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys095"), rowMapper, userId);
    }

    @Override
    public List<RoleEntity> getOwner(String userId) {
        RowMapper<RoleEntity> rowMapper = new BeanPropertyRowMapper<>(RoleEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys094"), rowMapper, userId);
    }

    @Transactional
    @Override
    public int auth(List<UserRoleEntity> list, String modifyUserId) {
        for (UserRoleEntity m : list) {
            String userId = m.getUserId();
            String roleId = m.getRoleId();
            String uuid = JoinCode.join(userId, roleId);
            jdbcTemplate.update(sqlText.getSql("sys096"), uuid, roleId, userId, modifyUserId);
        }
        return 1;
    }

    @Transactional
    @Override
    public int batchAuth(List<UserRoleEntity> list, String modifyUserId) {
        for (UserRoleEntity m : list) {
            String userId = m.getUserId();
            String roleId = m.getRoleId();
            String uuid = JoinCode.join(userId, roleId);
            try {
                jdbcTemplate.update(sqlText.getSql("sys096"), uuid, roleId, userId, modifyUserId);
            } catch (Exception e) {
                logger.info("用户[{}]已经拥有了角色[{}],无需重复授权", userId, roleId);
                logger.info(e.getMessage());
            }
        }
        return 1;
    }

    @Transactional
    @Override
    public int revoke(List<UserRoleEntity> list) {
        for (UserRoleEntity m : list) {
            String userId = m.getUserId();
            String roleId = m.getRoleId();
            String uuid = JoinCode.join(userId, roleId);
            jdbcTemplate.update(sqlText.getSql("sys097"), uuid);
        }
        return 1;
    }

    @Override
    public int add(RoleEntity roleEntity) {
        return jdbcTemplate.update(sqlText.getSql("sys026"),
                roleEntity.getRole_id(),
                roleEntity.getRole_name(),
                roleEntity.getCreate_user(),
                roleEntity.getRole_status_code(),
                roleEntity.getDomain_id(),
                roleEntity.getModify_user(),
                roleEntity.getCode_number());
    }

    @Override
    public int delete(List<RoleEntity> list) {
        for (RoleEntity m : list) {
            String roleId = m.getRole_id();
            String domainId = m.getDomain_id();
            jdbcTemplate.update(sqlText.getSql("sys027"), roleId, domainId);
        }
        return 1;
    }

    @Override
    public int update(RoleEntity roleEntity) {
        logger.debug("{},{},{},{}",
                roleEntity.getRole_name(),
                roleEntity.getRole_status_code(),
                roleEntity.getModify_user(),
                roleEntity.getRole_id());
        return jdbcTemplate.update(sqlText.getSql("sys050"),
                roleEntity.getRole_name(),
                roleEntity.getRole_status_code(),
                roleEntity.getModify_user(),
                roleEntity.getRole_id());
    }
}
