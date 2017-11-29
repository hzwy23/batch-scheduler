package com.asofdate.batch.service;

import com.asofdate.batch.dto.GroupDefineDto;
import com.asofdate.batch.dto.GroupTaskDto;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.entity.TaskDependencyEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/25.
 *
 * @author hzwy23
 */
public interface GroupTaskService {
    /**
     * 查询某个域中，某个批次所有任务
     *
     * @param domainId
     * @param batchId
     * @return List 批次中，所有的任务
     */
    List<GroupTaskEntity> findByBatchId(String domainId, String batchId);

    /**
     * 查询任务组中，所有的任务
     *
     * @param groupId
     */
    List<GroupTaskEntity> getTask(String groupId);

    /**
     * 查询某一个任务组中，指定任务的参数
     *
     * @param id 指定任务组中，指定任务的编码
     */
    List<TaskArgumentEntity> getTaskArg(String id);

    /**
     * 删除任务组中已经配置的任务
     *
     * @param id 任务组中已经配置的任务id
     */
    RetMsg deleteTask(String id);

    /**
     * 批量删除某一个任务组中的任务信息
     *
     * @param args
     */
    RetMsg deleteTask(Set<String> args);

    /**
     * 在任务组中新增任务
     *
     * @param id
     * @param groupId
     * @param taskId
     * @param domainId
     */
    RetMsg addTask(String id, String groupId, String taskId, String domainId);

    /**
     * 给任务组类型的参数赋值
     *
     * @param list
     */
    RetMsg addGroupArg(List<GroupDefineDto> list);

    /**
     * 查询任务组中，某一个指定的job可供选择的依赖任务列表
     *
     * @param groupId 任务组编码
     * @param jobKey  任务组中任务id
     */
    List<GroupTaskEntity> getGroupTask(String groupId, String jobKey);


    /**
     * 查询任务的依赖信息
     *
     * @param id 任务组中任务的id
     */
    List<GroupTaskEntity> getJobKeyDep(String id);

    /**
     * 给任务新增依赖
     *
     * @param list
     */
    RetMsg addTaskDependency(List<TaskDependencyEntity> list,String groupId);

    /**
     * 删除任务依赖
     *
     * @param uuid 任务id号
     */
    RetMsg deleteTaskDependency(String uuid);


    /**
     * 更新任务的坐标
     *
     * @param list 任务组
     * */
    RetMsg updateTaskLocation(List<GroupTaskDto> list);
}
