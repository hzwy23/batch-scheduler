package com.asofdate.batch.dao;

import com.asofdate.batch.entity.BatchGroupStatusEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public interface BatchGroupRunningDao {
    List<BatchGroupStatusEntity> findAll(String domainId);

    Integer getRatio(String batchId, String gid);

    BatchGroupStatusEntity getDetails(String batchId, String gid);
}
