package com.asofdate.batch.dao;

import com.asofdate.batch.dto.JobKeyDepDto;
import com.asofdate.batch.entity.TaskDependencyEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface TaskDependencyDao {
    List<TaskDependencyEntity> findAll(String domainId);

    List<JobKeyDepDto> findById(String domainId, String batchId);

}
