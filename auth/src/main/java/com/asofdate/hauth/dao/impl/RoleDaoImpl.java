package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.RoleDao;
import com.asofdate.hauth.entity.RoleEntity;
import com.asofdate.hauth.entity.UserRoleEntity;
import com.asofdate.hauth.sql.SqlText;
import com.asofdate.utils.JoinCode;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Override
    public List<RoleEntity> findAll() {
        RowMapper<RoleEntity> rowMapper = new BeanPropertyRowMapper<>(RoleEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys028"), rowMapper);
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
                log.info("用户[{}]已经拥有了角色[{}],无需重复授权", userId, roleId);
                log.info(e.getMessage());
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
                roleEntity.getModify_user(),
                roleEntity.getCode_number());
    }

    @Override
    public int delete(List<RoleEntity> list) {
        for (RoleEntity m : list) {
            String roleId = m.getRole_id();
            jdbcTemplate.update(sqlText.getSql("sys027"), roleId);
        }
        return 1;
    }

    @Override
    public int update(RoleEntity roleEntity) {
        log.debug("{},{},{},{}",
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
