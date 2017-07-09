package com.asofdate.batch.service;

import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.entity.TaskDefineEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
public interface TaskDefineService {
    /**
     * 查询批次中所有任务
     *
     * @param domainId
     * @param batchId
     */
    List<TaskDefineEntity> findAll(String domainId, String batchId);

    /**
     * 查询某个域中，所有的任务
     *
     * @param domainId
     */
    List<TaskDefineEntity> getAll(String domainId);

    /**
     * 添加任务
     *
     * @param m
     */
    RetMsg add(TaskDefineEntity m);

    /**
     * 删除任务
     *
     * @param m
     */
    RetMsg delete(List<TaskDefineEntity> m);

    /**
     * 更新任务
     *
     * @param m
     */
    RetMsg update(TaskDefineEntity m);

    List<TaskArgumentEntity> getTaskArg(String taskId);

    /**
     * 更新任务参数顺序
     *
     * @param sortId
     * @param uuid
     */
    RetMsg updateArgumentSort(String sortId, String uuid);

    /**
     * 删除任务参数
     *
     * @param uuid
     */
    RetMsg deleteArg(String uuid);

    /**
     * 查询参数类型
     *
     * @param argId
     */
    TaskArgumentEntity getArgType(String argId);

    /**
     * 给任务添加参数
     *
     * @param taskArgumentEntity
     */
    RetMsg addArg(TaskArgumentEntity taskArgumentEntity);

    /**
     * 更新任务参数值
     *
     * @param argValue
     * @param uuid
     */
    RetMsg updateArgValue(String argValue, String uuid);
}
