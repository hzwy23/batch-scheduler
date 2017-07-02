package com.asofdate.batch.service;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/28.
 *
 * @author hzwy23
 */
public interface GroupStatusService {
    /**
     * spring使用autowired注入bean后，调用这个方法，初始化对象属性
     *
     * @param domainId
     * @param batchId
     */
    void afterPropertiesSet(String domainId, String batchId);

    /**
     * 获取可以运行的任务组，
     * 将可以运行的任务组放到map中返回
     */
    Map getRunnableGroups();

    /**
     * 设置任务组状态为运行中
     *
     * @param gid 批次中任务组配置表中任务组的id
     */
    void setGroupRunning(String gid);

    /**
     * 设置任务组状态为已完成
     *
     * @param gid 批次中任务组配置表中任务组的id
     */
    void setGroupCompleted(String gid);

    /**
     * 设置批次中任务组执行失败
     *
     * @param gid 批次中任务组配置表中任务组的id
     */
    void setGroupError(String gid);

    /**
     * 获取任务组状态
     *
     * @param gid 批次中任务组配置表中任务组的id
     */
    int getGroupStatus(String gid);

    /**
     * 查询任务组是否可以运行，
     * 如果任务组的依赖项全部运行完成，则表示任务组可以运行
     *
     * @param gid 批次中任务组配置表中任务组id
     * @return true: 任务组可以运行， false： 任务组不满足运行条件
     */
    boolean isGroupRunable(String gid);
}
