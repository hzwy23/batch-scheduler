package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.HomeMenuEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/17.
 */
public interface HomeMenuService {
    List<HomeMenuEntity> findAuthedMenus(String userId, String typeId, String resId);
}
