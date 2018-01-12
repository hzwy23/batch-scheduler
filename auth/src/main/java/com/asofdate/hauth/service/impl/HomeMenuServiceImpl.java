package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.HomeMenuDao;
import com.asofdate.hauth.entity.HomeMenuEntity;
import com.asofdate.hauth.service.HomeMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/17.
 */
@Service
public class HomeMenuServiceImpl implements HomeMenuService {
    private final Logger logger = LoggerFactory.getLogger(HomeMenuServiceImpl.class);
    @Autowired
    public HomeMenuDao homeMenuDao;

    @Override
    public List<HomeMenuEntity> findAuthedMenus(String userId, String typeId, String resId) {
        List<HomeMenuEntity> list = homeMenuDao.findById(userId, typeId, resId);
        return list;
    }

    @Override
    public String getSubSystemUrl(String username, String resId) {
        try {
            return homeMenuDao.getSubSystemUrl(username, resId);
        } catch (DataAccessException e) {
            logger.info("获取子系统菜单信息失败，失败原因是：{}", e.getMessage());
            return "/";
        }
    }

    @Override
    public String getHomeUrl(String username) {
        try {
            return homeMenuDao.getHomeUrl(username);
        } catch (DataAccessException e) {
            logger.info("获取主系统菜单信息失败，失败原因是：{}", e.getMessage());
            return "/";
        }
    }
}
