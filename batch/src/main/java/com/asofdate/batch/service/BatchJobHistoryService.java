package com.asofdate.batch.service;

import com.asofdate.batch.entity.BatchJobHistoryEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 *
 * @author hzwy23
 */
public interface BatchJobHistoryService {
    /**
     * 查询批次中任务运行的历史信息
     *
     * @param id  历史信息表中，批次id
     * @param gid 批次中任务组id
     * @return 返回指定批次，指定任务组中任务的运行情况
     */
    List<BatchJobHistoryEntity> findAll(String id, String gid);
}
