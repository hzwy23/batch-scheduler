package com.asofdate.batch.dao;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public interface BatchGroupStatusDao {
    int init(String batchId, Map<String, Integer> map);

    int setGidStatus(String batchId, String gid, int status);

    int getGidStatus(String batchId, String gid);

    int getCompletedCnt(String batchId);

    int getTotalCnt(String batchId);

    int setGroupRunning(String batchId, String gid, int status);

    int setGroupEnd(String batchId, String gid, int status);
}
