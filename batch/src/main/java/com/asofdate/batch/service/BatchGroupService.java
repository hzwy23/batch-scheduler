package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchGroupDTO;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/25.
 *
 * @author hzwy23
 */
public interface BatchGroupService {
    /**
     * 查询批次中所有的任务组
     *
     * @param domainId
     * @param batchId
     */
    List<BatchGroupEntity> findByBatchId(String domainId, String batchId);

    /**
     * 查询批次中任务组列表信息
     *
     * @param batchId
     */
    List<BatchGroupEntity> getGroup(String batchId);

    /**
     * 添加任务组
     *
     * @param list
     */
    RetMsg addGroup(List<BatchGroupDTO> list);

    /**
     * 删除任务组
     *
     * @param list
     */
    RetMsg deleteGroup(List<BatchGroupDTO> list);

    /**
     * 获取批次中，任务组的依赖
     *
     * @param batchid 批次编码
     * @param id      批次任务组关系中，任务组的id号
     */
    List<BatchGroupEntity> getDependency(String batchid, String id);
}
