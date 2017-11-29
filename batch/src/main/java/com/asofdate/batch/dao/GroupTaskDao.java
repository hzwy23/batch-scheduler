package com.asofdate.batch.dao;

import com.asofdate.batch.dto.GroupDefineDto;
import com.asofdate.batch.dto.GroupTaskDto;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskDependencyEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface GroupTaskDao {
    List findAll(String domainId);

    List<GroupTaskEntity> getJobList(String groupId);

    List<GroupTaskEntity> getJobList(String groupId, String jobKey);

    String getTaskIdByJobKey(String jobKey);

    int deleteJob(String id);

    int deleteJob(Set<String> args);

    int addJob(String id, String groupId, String taskId, String domainId);

    int addJobArguments(List<GroupDefineDto> list);


    List<GroupTaskEntity> getTaskDependency(String id);

    int addTaskDependency(List<TaskDependencyEntity> list,String groupId);

    int deleteTaskDependency(String uuid);

    int updateTaskLocation(List<GroupTaskDto> list);
}
