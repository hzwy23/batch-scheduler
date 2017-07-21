package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchGroupRunningDao;
import com.asofdate.batch.entity.BatchGroupStatusEntity;
import com.asofdate.batch.service.BatchGroupRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Service
public class BatchGroupRunningServiceImpl implements BatchGroupRunningService {
    @Autowired
    private BatchGroupRunningDao batchGroupRunningDao;

    @Override
    public List<BatchGroupStatusEntity> findAll(String batchId, String asOfDate) {
        return batchGroupRunningDao.findAll(batchId, asOfDate);
    }

    @Override
    public Integer getRatio(String batchId, String gid, String asOfDate) {
        return batchGroupRunningDao.getRatio(batchId, gid, asOfDate);
    }

    @Override
    public BatchGroupStatusEntity getDetails(String batchId, String suiteKey, String asOfDate) {
        return batchGroupRunningDao.getDetails(batchId, suiteKey, asOfDate);
    }
}
