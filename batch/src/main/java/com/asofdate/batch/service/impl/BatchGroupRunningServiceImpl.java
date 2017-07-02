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
    public List<BatchGroupStatusEntity> findAll(String batchId) {
        return batchGroupRunningDao.findAll(batchId);
    }

    @Override
    public Integer getRatio(String batchId, String gid) {
        return batchGroupRunningDao.getRatio(batchId, gid);
    }

    @Override
    public BatchGroupStatusEntity getDetails(String batchId, String gid) {
        return batchGroupRunningDao.getDetails(batchId, gid);
    }
}
