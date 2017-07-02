package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.MenuDao;
import com.asofdate.hauth.entity.MenuEntity;
import com.asofdate.hauth.entity.ThemeValueEntity;
import com.asofdate.hauth.service.MenuService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Service
public class MenuServiceImpl implements MenuService {
    private final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuEntity> findAll() {
        return menuDao.findAll();
    }

    @Override
    public MenuEntity getDetails(String resId) {
        return menuDao.getDetails(resId);
    }

    @Override
    public RetMsg update(String resId, String resDesc, String resUpId) {
        try {
            String msg = menuDao.update(resId, resDesc, resUpId);
            if ("success".equals(msg)) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新菜单信息失败，请联系管理员", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public ThemeValueEntity getThemeDetails(String themeId, String resId) {
        return menuDao.getThemeDetails(themeId, resId);
    }

    @Override
    public String add(ThemeValueEntity themeValueEntity) {
        return menuDao.add(themeValueEntity);
    }

    @Override
    public String delete(String resId) {
        return menuDao.delete(resId);
    }

    @Override
    public String updateTheme(ThemeValueEntity themeValueEntity) {
        return menuDao.updateTheme(themeValueEntity);
    }
}
