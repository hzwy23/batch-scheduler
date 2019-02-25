package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.OrgDao;
import com.asofdate.hauth.dao.UserDao;
import com.asofdate.hauth.dto.UserDTO;
import com.asofdate.hauth.entity.OrgEntity;
import com.asofdate.hauth.entity.UserDetailsEntity;
import com.asofdate.hauth.entity.UserEntity;
import com.asofdate.hauth.sql.SqlText;
import com.asofdate.utils.CryptoAES;
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
 * Created by hzwy23 on 2017/6/18.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Autowired
    private OrgDao orgDao;

    @Override
    public List<UserEntity> findAll() {
        RowMapper<UserEntity> rowMapper = new BeanPropertyRowMapper<>(UserEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys017"), rowMapper);
    }

    @Override
    public List<UserEntity> findAll(String orgId, String statusCd) {
        List<UserEntity> list = findAll();
        if ("0".equals(statusCd) || "1".equals(statusCd)) {
            for (int i = 0; i < list.size(); i++) {
                if (!statusCd.equals(list.get(i).getStatusCd())) {
                    list.remove(i);
                    i--;
                }
            }
        }
        if (orgId != null && !orgId.isEmpty()) {
            List<OrgEntity> orgList = orgDao.findSub(orgId);
            Set<String> set = new HashSet<>();
            for (OrgEntity om : orgList) {
                set.add(om.getOrg_id());
            }
            set.add(orgId);
            for (int i = 0; i < list.size(); i++) {
                if (!set.contains(list.get(i).getOrgUnitId())) {
                    list.remove(i);
                    i--;
                }
            }
        }
        return list;
    }

    @Transactional
    @Override
    public int add(UserEntity userEntity) {
        jdbcTemplate.update(sqlText.getSql("sys018"),
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getCreateUser(),
                userEntity.getUserEmail(),
                userEntity.getUserPhone(),
                userEntity.getOrgUnitId(),
                userEntity.getModifyUser());
        String password = CryptoAES.getInstance().aesEncrypt(userEntity.getUserPasswd());
        return jdbcTemplate.update(sqlText.getSql("sys019"),
                userEntity.getUserId(), password, 0);
    }

    @Transactional
    @Override
    public int delete(List<UserEntity> list) {
        for (UserEntity m : list) {
            jdbcTemplate.update(sqlText.getSql("sys007"),
                    m.getUserId(),
                    m.getOrgUnitId());
        }
        return 1;
    }

    @Override
    public int update(UserEntity userEntity) {
        return jdbcTemplate.update(sqlText.getSql("sys021"),
                userEntity.getUserName(),
                userEntity.getUserPhone(),
                userEntity.getUserEmail(),
                userEntity.getModifyUser(),
                userEntity.getOrgUnitId(),
                userEntity.getUserId());
    }

    @Override
    public int changePassword(UserDTO m) {
        String userId = m.getUserId();
        String newPd = m.getNewPasswd();
        String passwd = CryptoAES.getInstance().aesEncrypt(newPd);
        return jdbcTemplate.update(sqlText.getSql("sys015"),
                passwd, userId);
    }

    @Override
    public int changeStatus(String userId, String status) {
        return jdbcTemplate.update(sqlText.getSql("sys016"), status, userId);
    }

    @Override
    public UserDetailsEntity findById(String userId) {
        RowMapper<UserDetailsEntity> rowMapper = new BeanPropertyRowMapper<UserDetailsEntity>(UserDetailsEntity.class);
        UserDetailsEntity detailsEntity = jdbcTemplate.queryForObject(sqlText.getSql("sys023"), rowMapper, userId);
        String domainId = jdbcTemplate.queryForObject("select domain_id from sys_domain_authorization where user_id = ? and default_domain = 1", String.class, userId);
        detailsEntity.setDomainId(domainId);
        return detailsEntity;
    }

    @Override
    public int changePasswd(String newPasswd, String userId, String oldPasswd) {
        return jdbcTemplate.update(sqlText.getSql("sys014"), newPasswd, userId, oldPasswd);
    }
}
