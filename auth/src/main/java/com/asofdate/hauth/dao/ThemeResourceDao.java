package com.asofdate.hauth.dao;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/17.
 */
public interface ThemeResourceDao {
    List findByThemeId(String themeId);
}
