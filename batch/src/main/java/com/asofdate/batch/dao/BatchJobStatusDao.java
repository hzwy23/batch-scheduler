package com.asofdate.batch.dao;

import com.asofdate.batch.dto.BatchRunConfDto;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/14.
 */
public interface BatchJobStatusDao {
    int init(BatchRunConfDto confDto, Map<String, Integer> map);

    int setJobStatus(BatchRunConfDto conf, String jobId, int status);

    int getJobStatus(BatchRunConfDto conf, String jobId);

    int getCompletedCnt(BatchRunConfDto conf);

    int getTotalCnt(BatchRunConfDto conf);

    int setJobRunning(BatchRunConfDto conf, String jobId, int status);

    int setJobEnd(BatchRunConfDto conf, String jobId, int status);
}
