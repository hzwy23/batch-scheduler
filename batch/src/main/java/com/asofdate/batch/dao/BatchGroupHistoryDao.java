package com.asofdate.batch.dao;

import com.asofdate.batch.entity.BatchGroupHistoryEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public interface BatchGroupHistoryDao {
    List<BatchGroupHistoryEntity> findAll(String uuid);
}
