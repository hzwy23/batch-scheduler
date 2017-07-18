package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupTaskEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/28.
 *
 * @author hzwy23
 */
public interface JobKeyStatusService {
    /**
     * spring使用autowired注入bean后，调用这个方法初始化对象属性
     *
     * @param confDto
     */
    void afterPropertiesSet(BatchRunConfDto confDto, List<BatchGroupEntity> suiteKeyList, List<GroupTaskEntity> jobKeyList);

    /**
     * 查询任务状态
     *
     * @param suiteKey
     * @param jobKey
     * @retum int 任务状态标识
     */
    int getJobStatus(String suiteKey, String jobKey);

    int getJobStatus(String jobId);


    /**
     * 设置任务状态为完成
     *
     * @param uid
     */
    void setJobCompleted(String uid);

    /**
     * 设置任务状态为运行中
     *
     * @param uid
     */
    void setJobRunning(String uid);

    /**
     * 设置任务状态为错误
     *
     * @param uid
     */
    void setJobError(String uid);

    /**
     * 检查是否存在运行错误的job
     */
    boolean hasError();

    /**
     * 获取批次中，所有的任务
     */
    Map<String, Integer> getAllJob();
}
