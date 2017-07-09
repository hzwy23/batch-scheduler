package com.asofdate.batch.dao;

import com.asofdate.batch.entity.TaskArgumentEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface TaskArgumentDao {
    List findAll(String domainId);

    List<TaskArgumentEntity> getTaskArg(String taskId);

    int updateSort(String sortId, String uuid);

    int deleteArg(String uuid);

    TaskArgumentEntity getArgType(String argId);

    int addArg(TaskArgumentEntity taskArgumentEntity);

    int updateArgValue(String argValue, String uuid);
}
