package com.asofdate.hauth.dao;

import com.asofdate.hauth.entity.MenuEntity;
import com.asofdate.hauth.entity.ThemeValueEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface MenuDao {

    List<MenuEntity> findAll();

    MenuEntity getDetails(String resId);

    String add(ThemeValueEntity themeValueEntity);

    String delete(String resId);

    String update(String resId, String resDesc, String resUpId);

    ThemeValueEntity getThemeDetails(String themeId, String resId);

    String updateTheme(ThemeValueEntity themeValueEntity);

}
