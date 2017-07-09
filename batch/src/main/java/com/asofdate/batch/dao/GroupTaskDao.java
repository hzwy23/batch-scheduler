package com.asofdate.batch.dao;

import com.asofdate.batch.dto.GroupDefineDto;
import com.asofdate.batch.entity.GroupTaskEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface GroupTaskDao {
    List findAll(String domainId);

    List<GroupTaskEntity> getTask(String groupId);

    String getTaskId(String id);

    int deleteTask(String id);

    int deleteTask(Set<String> args);

    int addTask(String id, String groupId, String taskId, String domainId);

    int addArg(List<GroupDefineDto> list);
}
