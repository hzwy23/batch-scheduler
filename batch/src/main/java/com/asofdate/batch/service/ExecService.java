package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/13.
 */
public interface ExecService {
    RetMsg echo(ExecLogEntity row);

    List<ExecLogEntity> query(String id, String jobId);

    RetMsg init(BatchRunConfDto confDto);
}
