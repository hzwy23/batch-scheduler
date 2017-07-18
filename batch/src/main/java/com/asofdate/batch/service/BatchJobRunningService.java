package com.asofdate.batch.service;

import com.asofdate.batch.entity.BatchJobStatusEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 *
 * @author hzwy23
 */
public interface BatchJobRunningService {
    /**
     * 查询批次中，任务组的状态信息
     *
     * @param batchId 批次编码
     * @param suiteKey     批次任务组的id
     * @return 返回批次中，某一个任务组的所有任务
     */
    List<BatchJobStatusEntity> findAll(String batchId, String suiteKey, String asOfDate);

    /**
     * 查询批次中，某一个指定任务的详细信息
     *
     * @param batchId
     * @param suiteKey
     * @param jobKey
     * @return 批次中某一个指定任务的详细信息
     */
    BatchJobStatusEntity getDetails(String batchId, String suiteKey, String jobKey);
}
