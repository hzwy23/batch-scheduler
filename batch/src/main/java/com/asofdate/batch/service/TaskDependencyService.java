package com.asofdate.batch.service;

import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskDependencyEntity;
import com.asofdate.utils.RetMsg;
import org.json.JSONArray;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface TaskDependencyService {
    List<TaskDependencyEntity> findById(String domainId, String batchId);

    Set<String> getTaskDependency(String gid, String id);

    /**
     * 使用spring注入bean后，调用这个方法初始化对象属性
     *
     * @param domainId
     * @param batchId
     */
    void afterPropertiesSet(String domainId, String batchId);

    /**
     * 查询任务的依赖信息
     *
     * @param id 任务组中任务的id
     */
    List<GroupTaskEntity> getTaskDependency(String id);

    /**
     * 查询任务组中，某一个指定的job可供选择的依赖任务列表
     *
     * @param groupId 任务组编码
     * @param id      任务组中任务id
     */
    List<GroupTaskEntity> getGroupTask(String groupId, String id);

    /**
     * 给任务新增依赖
     *
     * @param jsonArray
     */
    RetMsg addTaskDependency(JSONArray jsonArray);

    /**
     * 删除任务依赖
     *
     * @param uuid 任务id号
     */
    RetMsg deleteTaskDependency(String uuid);
}
