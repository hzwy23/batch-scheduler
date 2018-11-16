package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchJobStatusDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.service.JobKeyStatusService;
import com.asofdate.batch.utils.JobStatus;
import com.asofdate.utils.JoinCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/28.
 */
@Service
@Scope("prototype")
public class JobKeyStatusServiceImpl implements JobKeyStatusService {
    @Autowired
    private BatchJobStatusDao batchJobStatusDao;

    private BatchRunConfDto conf;

    /**
     * 批次中任务状态管理
     * 外层Map是batch中group的嵌套
     * key 表示任务组中任务的id,每一个任务的id都不一样
     * value: 表示任务的状态.
     * 0: 初始化
     * 1: 运行中
     * 2: 已完成
     * 3: 错误
     */
    private Map<String, Integer> jobKeyStatus;

    @Override
    public Map<String, Integer> getAllJob() {
        return jobKeyStatus;
    }

    // 初始化两个变量
    @Override
    public void afterPropertiesSet(BatchRunConfDto confDto, List<BatchGroupEntity> suiteKeyList, List<GroupTaskEntity> jobKeyList) {
        this.conf = confDto;
        jobKeyStatus = new HashMap<String, Integer>();
        /*
         * 初始化全部任务组
         * */
        for (BatchGroupEntity gl : suiteKeyList) {
            for (GroupTaskEntity tl : jobKeyList) {
                String groupId = tl.getGroupId();
                if (groupId.equals(gl.getGroupId())) {
                    String jobId = JoinCode.join(gl.getSuiteKey(), tl.getJobKey());
                    jobKeyStatus.put(jobId, JobStatus.Job_STATUS_INIT);
                }
            }
        }
        batchJobStatusDao.init(conf, jobKeyStatus);
    }

    /**
     * 获取任务状态
     *
     * @param suiteKey 表示任务组id
     * @param jobKey   表示任务id;
     */
    public int getJobStatus(String suiteKey, String jobKey) {
        String jobId = JoinCode.join(suiteKey, jobKey);
        if (jobKeyStatus.containsKey(jobId)) {
            return jobKeyStatus.get(jobId);
        }
        return JobStatus.Job_STATUS_ERROR;
    }


    /*
     * @param String uid 表示任务组id与任务id的组合,简称uid
     * */
    public int getJobStatus(String jobId) {
        if (jobKeyStatus.containsKey(jobId)) {
            return jobKeyStatus.get(jobId);
        }
        return JobStatus.Job_STATUS_ERROR;
    }

    /*
     * 设置任务状态为已完成
     * @param String gid 表示任务组id
     * @param String id  表示任务id
     * */
    @Override
    public void setJobCompleted(String uid) {
        jobKeyStatus.put(uid, JobStatus.Job_STATUS_COMPLETED);
        batchJobStatusDao.setJobEnd(conf, uid, JobStatus.Job_STATUS_COMPLETED);
    }

    @Override
    public void setJobRunning(String jobId) {
        jobKeyStatus.put(jobId, JobStatus.Job_STATUS_RUNNING);
        batchJobStatusDao.setJobRunning(conf, jobId, JobStatus.Job_STATUS_RUNNING);
    }

    @Override
    public void setJobError(String jobId) {
        // 修改内存中任务状态为失败
        jobKeyStatus.put(jobId, JobStatus.Job_STATUS_ERROR);

        // 修改数据库中的任务状态为失败
        batchJobStatusDao.setJobEnd(conf, jobId, JobStatus.Job_STATUS_ERROR);
    }

    public boolean hasError() {
        return jobKeyStatus.containsValue(JobStatus.Job_STATUS_ERROR);
    }

}
