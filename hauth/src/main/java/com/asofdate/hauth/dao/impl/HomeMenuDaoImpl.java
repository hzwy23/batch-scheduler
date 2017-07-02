package com.asofdate.hauth.dao.impl;

import com.asofdate.hauth.dao.*;
import com.asofdate.hauth.entity.HomeMenuEntity;
import com.asofdate.hauth.entity.ResourceEntity;
import com.asofdate.hauth.entity.ThemeResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by hzwy23 on 2017/5/17.
 */
@Repository
public class HomeMenuDaoImpl implements HomeMenuDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public UserThemeDao userThemeDao;

    @Autowired
    public ThemeResourceDao themeResourceDao;

    @Autowired
    public ResourceDao resourceDao;

    @Autowired
    private UserResourceDao userResourceDao;

    private String getThemeId(String userId) {
        return userThemeDao.findById(userId);
    }

    private List getSubResource(String resUpId) {
        List<ResourceEntity> list = resourceDao.findSubByUpId(resUpId);
        return list;
    }

    private List getThemeResource(String themeId) {
        return themeResourceDao.findByThemeId(themeId);
    }

    /*
    * 将菜单资源树转从List转换成Map类型
    * 方便后边的匹配操作
    * key : res_id
    * value: ResourceEntity
    * */
    private Map<String, ResourceEntity> list2Map(List<ResourceEntity> list, String typeId) {
        Map<String, ResourceEntity> map = new HashMap<String, ResourceEntity>();
        for (ResourceEntity m : list) {
            if (m.getRes_type().equals(typeId)) {
                map.put(m.getRes_id(), m);
            }
        }
        return map;
    }

    @Override
    public List findById(String userId, String typeId, String resId) {

        Set<String> set = userResourceDao.findAll(userId);
        // 获取用户配置的主题信息
        String themeId = getThemeId(userId);

        //获取主题对应的资源信息
        List<ThemeResourceEntity> list = getThemeResource(themeId);

        // 获取指定菜单的所以下级菜单信息
        List<ResourceEntity> resourceList = getSubResource(resId);

        // 将指定资源的所有下级信息转换成Map
        // 方便后边匹配查询
        Map<String, ResourceEntity> resourceMap = list2Map(resourceList, typeId);

        List<HomeMenuEntity> rst = new ArrayList<HomeMenuEntity>();
        for (ThemeResourceEntity m : list) {
            if (!set.contains(m.getRes_id())) {
                continue;
            }
            if (resourceMap.containsKey(m.getRes_id())) {
                HomeMenuEntity homeMenuEntity = new HomeMenuEntity();
                homeMenuEntity.setGroup_id(m.getGroup_id());
                homeMenuEntity.setRes_bg_color(m.getRes_bg_color());
                homeMenuEntity.setRes_class(m.getRes_class());
                homeMenuEntity.setRes_id(m.getRes_id());
                homeMenuEntity.setRes_img(m.getRes_img().replaceFirst("^/static", ""));
                homeMenuEntity.setRes_name(resourceMap.get(m.getRes_id()).getRes_name());
                homeMenuEntity.setRes_open_type(m.getRes_type());
                homeMenuEntity.setRes_url(m.getRes_url().replaceFirst("^/views", ""));
                rst.add(homeMenuEntity);
            }
        }
        return rst;
    }
}
