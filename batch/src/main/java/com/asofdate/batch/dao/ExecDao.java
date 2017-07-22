package com.asofdate.batch.dao;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/13.
 */
public interface ExecDao {
    int insert(ExecLogEntity row);

    List<ExecLogEntity> query(String id, String jobId);

    void init(BatchRunConfDto confDto);
}
