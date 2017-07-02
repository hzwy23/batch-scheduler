package com.asofdate.batch.dao;

import com.asofdate.batch.entity.BatchArgumentEntiry;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
public interface BatchArgumentDao {
    /**
     * 查询某一个域中，某一个批次中所有任务包含的参数信息
     *
     * @param domainId 域编码
     * @param batchId  批次编码
     */
    List findAll(String domainId, String batchId);

    /**
     * 查询某一个批次所有的批次类型参数信息
     *
     * @param batchId 批次编码
     * @return 返回某一个批次所有的批次参数类型的值
     */
    List<BatchArgumentEntiry> findBatchArgsById(String batchId);

    String getAsOfDate(String batchId);

    int add(List<BatchArgumentEntiry> list);
}
