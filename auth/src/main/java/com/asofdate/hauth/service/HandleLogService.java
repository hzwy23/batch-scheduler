package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.HandleLogEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface HandleLogService {
    List<HandleLogEntity> findAll();

    List<HandleLogEntity> findAll(Integer offset, Integer limit);

    Integer getTotal();
}
