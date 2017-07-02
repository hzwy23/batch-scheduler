package com.asofdate.batch.service;

import com.asofdate.batch.entity.BatchGroupHistoryEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 *
 * @author hzwy23
 */
public interface BatchGroupHistoryService {
    /**
     * 查询批次中，任务组的运行信息
     *
     * @param uuid 批次运行历史表中的id号
     * @return List 返回任务组的运行信息
     */
    List<BatchGroupHistoryEntity> findAll(String uuid);
}
