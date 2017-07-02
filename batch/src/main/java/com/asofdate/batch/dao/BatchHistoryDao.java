package com.asofdate.batch.dao;

import com.asofdate.batch.entity.BatchHistoryEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
public interface BatchHistoryDao {
    List<BatchHistoryEntity> findAll(String domainId);

    int delete(List<BatchHistoryEntity> list);
}
