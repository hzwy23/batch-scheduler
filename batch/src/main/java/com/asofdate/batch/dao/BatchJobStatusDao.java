package com.asofdate.batch.dao;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/14.
 */
public interface BatchJobStatusDao {
    int init(String batchId, Map<String, Integer> map);

    int setJobStatus(String batchId, String jobId, int status);

    int getJobStatus(String batchId, String jobId);

    int getCompletedCnt(String batchId);

    int getTotalCnt(String batchId);

    int setJobRunning(String batchId, String jobId, int status);

    int setJobEnd(String batchId, String jobId, int status);
}
