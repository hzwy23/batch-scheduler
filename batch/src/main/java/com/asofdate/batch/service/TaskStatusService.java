package com.asofdate.batch.service;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/28.
 *
 * @author hzwy23
 */
public interface TaskStatusService {
    /**
     * spring使用autowired注入bean后，调用这个方法初始化对象属性
     *
     * @param domainId
     * @param batchId
     */
    void afterPropertiesSet(String domainId, String batchId);

    /**
     * 查询任务组是否运行完成
     *
     * @param gid
     * @param groupId
     * @return true ： 运行完成， false： 未完成
     */
    boolean isGroupCompleted(String gid, String groupId);

    /**
     * 查询任务状态
     *
     * @param gid
     * @param id
     * @retum int 任务状态标识
     */
    int getTaskStatus(String gid, String id);

    /**
     * 查询任务是否可以被执行，如果任务的依赖都满足要求，则返回true，否则返回false
     *
     * @param gid
     * @param id
     */
    boolean isTaskRunnable(String gid, String id);

    /**
     * 设置任务状态为完成
     *
     * @param uid
     */
    void setTaskCompleted(String uid);

    /**
     * 设置任务状态为运行中
     *
     * @param uid
     */
    void setTaskRunning(String uid);

    /**
     * 设置任务状态为错误
     *
     * @param uid
     */
    void setTaskError(String uid);

    /**
     * 查询任务组中可以运行的任务
     *
     * @param gid
     * @param groupId
     */
    Map getRunnableTasks(String gid, String groupId);

    /**
     * 查询批次是否运行完成
     *
     * @return true：批次运行完成， false： 批次没有运行完成
     */
    boolean isBatchCompleted();

    /**
     * 查询批次是否有执行错误的任务
     *
     * @return true：有错误的任务， false： 没有错误的任务
     */
    boolean isError();

    /**
     * 获取批次中，所有的任务
     */
    Map<String, Integer> getAll();
}
