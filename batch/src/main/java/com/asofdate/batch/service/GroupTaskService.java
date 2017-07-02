package com.asofdate.batch.service;

import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.utils.RetMsg;
import org.json.JSONArray;

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
    JSONArray getTask(String groupId);

    /**
     * 查询某一个任务组中，指定任务的参数
     *
     * @param id 指定任务组中，指定任务的编码
     */
    JSONArray getTaskArg(String id);

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
     * @param jsonArray
     */
    RetMsg addGroupArg(JSONArray jsonArray);
}
