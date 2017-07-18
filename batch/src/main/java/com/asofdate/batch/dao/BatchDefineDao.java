package com.asofdate.batch.dao;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface BatchDefineDao {
    List findAll(String domainId);

    List<BatchDefineEntity> getRunning(String domainId);

    int add(BatchDefineEntity m);

    String delete(List<BatchDefineEntity> m);

    int update(BatchDefineEntity m);

    int getStatus(String batchId);

    int batchPagging(String batchId, String asOfDate);

    int setStatus(String batchId, int status);

    int updateAsofdate(String asofdate, String batchId);

    int runBatchInit(String batchId);

    int destoryBatch(String batchId, String retMsg, int status);

    int saveHistory(String batchId);

    String getBatchAsOfDate(String batchId);

    BatchDefineEntity findDetailsByBatchId(String batchId);
}
