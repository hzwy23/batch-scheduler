package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchJobRunningDao;
import com.asofdate.batch.entity.BatchJobStatusEntity;
import com.asofdate.batch.service.BatchJobRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Service
public class BatchJobRunningServiceImpl implements BatchJobRunningService {
    @Autowired
    private BatchJobRunningDao batchJobRunningDao;

    @Override
    public List<BatchJobStatusEntity> findAll(String batchId, String suiteKey, String asOfDate) {
        return batchJobRunningDao.findAll(batchId, suiteKey, asOfDate);
    }

    @Override
    public BatchJobStatusEntity getDetails(String batchId, String suiteKey, String jobKey) {
        return batchJobRunningDao.getDetails(batchId, suiteKey, jobKey);
    }
}
