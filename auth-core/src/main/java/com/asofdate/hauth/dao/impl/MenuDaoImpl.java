package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.MenuDao;
import com.asofdate.hauth.dao.ResourceDao;
import com.asofdate.hauth.entity.MenuEntity;
import com.asofdate.hauth.entity.ResourceEntity;
import com.asofdate.hauth.entity.ThemeValueEntity;
import com.asofdate.hauth.sql.SqlText;
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
public class MenuDaoImpl implements MenuDao {
    private final Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlText sqlText;

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<MenuEntity> findAll() {
        RowMapper<MenuEntity> rowMapper = new BeanPropertyRowMapper<>(MenuEntity.class);
        return jdbcTemplate.query(sqlText.getSql("sys071"), rowMapper);
    }

    @Override
    public MenuEntity getDetails(String resId) {
        RowMapper<MenuEntity> rowMapper = new BeanPropertyRowMapper<>(MenuEntity.class);
        List<MenuEntity> list = jdbcTemplate.query(sqlText.getSql("sys089"), rowMapper, resId);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public String add(ThemeValueEntity themeValueEntity) {
        try {
            jdbcTemplate.update(sqlText.getSql("sys072"),
                    themeValueEntity.getRes_id(),
                    themeValueEntity.getRes_name(),
                    themeValueEntity.getRes_attr(),
                    themeValueEntity.getRes_up_id(),
                    themeValueEntity.getRes_type());

            String resType = themeValueEntity.getRes_type();
            if ("0".equals(resType) || "1".equals(resType) || "2".equals(resType)) {
                jdbcTemplate.update(sqlText.getSql("sys008"),
                        themeValueEntity.getTheme_id(),
                        themeValueEntity.getRes_id(),
                        themeValueEntity.getRes_url(),
                        themeValueEntity.getRes_open_type(),
                        themeValueEntity.getRes_bg_color(),
                        themeValueEntity.getRes_class(),
                        themeValueEntity.getGroup_id(),
                        themeValueEntity.getRes_img(),
                        themeValueEntity.getSort_id(),
                        themeValueEntity.getNew_iframe());
            }
            return "success";
        } catch (Exception e) {
            logger.info(e.getMessage());
            return e.getMessage();
        }
    }

    @Transactional
    @Override
    public String delete(String resId) {
        List<ResourceEntity> list = resourceDao.findSubByUpId(resId);
        try {
            jdbcTemplate.update(sqlText.getSql("sys077"), resId);
            for (ResourceEntity m : list) {
                jdbcTemplate.update(sqlText.getSql("sys077"), m.getRes_id());
            }
            return "success";
        } catch (Exception e) {
            logger.info(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String update(String resId, String resDesc, String resUpId) {
        jdbcTemplate.update(sqlText.getSql("sys005"), resDesc, resUpId, resId);
        return "success";
    }

    @Override
    public ThemeValueEntity getThemeDetails(String themeId, String resId) {
        RowMapper<ThemeValueEntity> rowMapper = new BeanPropertyRowMapper<>(ThemeValueEntity.class);
        List<ThemeValueEntity> list = jdbcTemplate.query(sqlText.getSql("sys070"), rowMapper, themeId, resId);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String updateTheme(ThemeValueEntity themeValueEntity) {
        String resId = themeValueEntity.getRes_id();
        String themeId = themeValueEntity.getTheme_id();
        Integer cnt = jdbcTemplate.queryForObject(sqlText.getSql("sys006"), Integer.class, themeId, resId);
        if (cnt == 0) {
            try {
                jdbcTemplate.update(sqlText.getSql("sys008"),
                        themeValueEntity.getTheme_id(),
                        themeValueEntity.getRes_id(),
                        themeValueEntity.getRes_url(),
                        themeValueEntity.getRes_open_type(),
                        themeValueEntity.getRes_bg_color(),
                        themeValueEntity.getRes_class(),
                        themeValueEntity.getGroup_id(),
                        themeValueEntity.getRes_img(),
                        themeValueEntity.getSort_id());
                return "success";
            } catch (Exception e) {
                logger.info(e.getMessage());
                return e.getMessage();
            }
        }
        try {
            jdbcTemplate.update(sqlText.getSql("sys009"),
                    themeValueEntity.getRes_url(),
                    themeValueEntity.getRes_bg_color(),
                    themeValueEntity.getRes_class(),
                    themeValueEntity.getRes_img(),
                    themeValueEntity.getGroup_id(),
                    themeValueEntity.getSort_id(),
                    themeValueEntity.getRes_open_type(),
                    themeValueEntity.getNew_iframe(),
                    themeValueEntity.getTheme_id(),
                    themeValueEntity.getRes_id());
            return "success";
        } catch (Exception e) {
            logger.info(e.getMessage());
            return e.getMessage();
        }
    }
}
