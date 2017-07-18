package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchGroupEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/28.
 *
 * @author hzwy23
 */
public interface SuiteKeyStatusService {
    /**
     * spring使用autowired注入bean后，调用这个方法，初始化对象属性
     *
     * @param conf
     **/
    void afterPropertiesSet(BatchRunConfDto conf, List<BatchGroupEntity> list);

    /**
     * 设置任务组状态为运行中
     *
     * @param suiteKey 批次中任务组配置表中任务组的id
     */
    void setSuiteRunning(String suiteKey);

    /**
     * 设置任务组状态为已完成
     *
     * @param suiteKey 批次中任务组配置表中任务组的id
     */
    void setSuiteCompleted(String suiteKey);

    /**
     * 设置批次中任务组执行失败
     *
     * @param suiteKey 批次中任务组配置表中任务组的id
     */
    void setSuiteError(String suiteKey);

    /**
     * 获取任务组状态
     *
     * @param suiteKey 批次中任务组配置表中任务组的id
     */
    int getSuiteStatus(String suiteKey);


    /**
     * 查询批次中所有任务组是否执行完成
     */
    boolean isCompleted();

}
