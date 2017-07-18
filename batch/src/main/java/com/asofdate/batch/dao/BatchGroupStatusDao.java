package com.asofdate.batch.dao;

import com.asofdate.batch.dto.BatchRunConfDto;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public interface BatchGroupStatusDao {
    int init(BatchRunConfDto conf, Map<String, Integer> map);

    int setSuiteKeyStatus(BatchRunConfDto conf, String suiteKey, int status);

    int getSuiteKeyStatus(BatchRunConfDto conf, String suiteKey);

    int getCompletedCnt(BatchRunConfDto conf);

    int getTotalCnt(BatchRunConfDto conf);

    int setGroupRunning(BatchRunConfDto conf, String suiteKey, int status);

    int setGroupEnd(BatchRunConfDto conf, String suiteKey, int status);
}
