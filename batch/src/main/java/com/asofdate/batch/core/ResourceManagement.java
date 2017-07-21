package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.service.*;
import com.asofdate.batch.utils.GroupStatus;
import com.asofdate.batch.utils.JobStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.*;

/**
 * Created by hzwy23 on 2017/7/16.
 */
@Configuration
@Scope("prototype")
public class ResourceManagement {
    @Autowired
    public JobKeyStatusService jobKeyStatusService;
    @Autowired
    private BatchGroupService batchGroupService;
    @Autowired
    private GroupTaskService groupTaskService;
    @Autowired
    private SuiteKeyDependenceService suiteKeyDependenceService;
    @Autowired
    private JobKeyDependenceService jobKeyDependenceService;
    @Autowired
    private SuiteKeyStatusService suiteKeyStatusService;
    @Autowired
    private ArgumentService argumentService;

    private List<BatchGroupEntity> suiteKeyList;

    private List<GroupTaskEntity> jobKeyList;

    private Map<String, Set<String>> suiteJobMap;

    public void afterPropertiesSet(BatchRunConfDto conf) {
        suiteKeyList = batchGroupService.findByBatchId(conf.getDomainId(), conf.getBatchId());
        jobKeyList = groupTaskService.findByBatchId(conf.getDomainId(), conf.getBatchId());
        suiteKeyStatusService.afterPropertiesSet(conf, suiteKeyList);
        jobKeyStatusService.afterPropertiesSet(conf, suiteKeyList, jobKeyList);
        suiteKeyDependenceService.afterPropertiesSet(conf);
        jobKeyDependenceService.afterPropertiesSet(conf);

        suiteJobMap = new HashMap<>();
        for (BatchGroupEntity bg : suiteKeyList) {
            for (GroupTaskEntity gt : jobKeyList) {
                String groupId = gt.getGroupId();
                if (groupId.equals(bg.getGroupId())) {
                    if (suiteJobMap.containsKey(bg.getSuiteKey())) {
                        suiteJobMap.get(bg.getSuiteKey()).add(gt.getJobKey());
                    } else {
                        Set set = new HashSet();
                        set.add(gt.getJobKey());
                        suiteJobMap.put(bg.getSuiteKey(), set);
                    }
                }
            }
        }
        argumentService.afterPropertySet(conf, jobKeyList);
    }

    /*
    * @return: Map<Key,Value>
    *     key: 表示批次中配置的需要运行的任务组id, 这个id不是任务组编码,而是配置任务组依赖关系时,生成的随机唯一性编码
    *     value: 是任务组的详细信息,包括任务组编码,所属域等等
    * */
    public Set<String> getRunnableSuite() {
        Set<String> set = new HashSet<>();
        for (BatchGroupEntity m : suiteKeyList) {
            set.remove(m.getSuiteKey());
            /*
            * 如果任务组状态不是初始化值
            * 表示任务组已经被启动, 则不允许在此加入预备运行状态中.
            * */
            if (suiteKeyStatusService.getSuiteStatus(m.getSuiteKey()) != GroupStatus.SUITE_KEY_STATUS_INIT) {
                continue;
            }

            if (isSuiteRunable(m.getSuiteKey())) {
                set.add(m.getSuiteKey());
            }
        }
        return set;
    }


    /**
     * 判断任务组是否可以转换成执行状态
     * 只有当任务组处于初始化状态,且其依赖的上级任务组已经运行完成
     * 那么才能将这个任务组设置成执行状态
     *
     * @param suiteKey 表示任务组的id
     */
    public boolean isSuiteRunable(String suiteKey) {
        Set<GroupDependencyEntity> groupDependencyEntities = suiteKeyDependenceService.getSuiteDependency(suiteKey);
        if (groupDependencyEntities == null) {
            return true;
        }
        for (GroupDependencyEntity gp : groupDependencyEntities) {
            int statusCd = suiteKeyStatusService.getSuiteStatus(gp.getUpSuiteKey());
            if (GroupStatus.SUITE_KEY_STATUS_COMPLETED == statusCd) {
                continue;
            }
            return false;
        }
        return true;
    }


    public boolean isSuiteCompleted(String suiteKey) {
        if (suiteJobMap.containsKey(suiteKey)) {
            Set<String> jobKeySet = suiteJobMap.get(suiteKey);
            for (String jobKey : jobKeySet) {
                if (jobKeyStatusService.getJobStatus(suiteKey, jobKey) != GroupStatus.SUITE_KEY_STATUS_COMPLETED) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }


    /**
     * 判断任务组中的任务是否可以执行
     *
     * @param suiteKey 表示任务组的id;
     * @param jobKey   表示任务的id;
     */
    public boolean isJobRunnable(String suiteKey, String jobKey) {
        Set<String> set = jobKeyDependenceService.getJobDependence(suiteKey, jobKey);
        if (set == null) {
            return true;
        }
        for (String m : set) {
            if (JobStatus.Job_STATUS_COMPLETED == jobKeyStatusService.getJobStatus(m)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public Set<String> getRunnableJob(String suiteKey) {
        Set<String> set = new HashSet<>();
        Set<String> jobKeySet = suiteJobMap.get(suiteKey);

        for (String jobKey : jobKeySet) {
            set.remove(jobKey);
            int jobStatus = jobKeyStatusService.getJobStatus(suiteKey, jobKey);
            if (jobStatus != JobStatus.Job_STATUS_INIT) {
                continue;
            }
            if (isJobRunnable(suiteKey, jobKey)) {
                set.add(jobKey);
            }
        }
        return set;
    }

    public boolean isBatchCompleted() {
        return suiteKeyStatusService.isCompleted();
    }

    public JobKeyStatusService getJobKeyStatusService() {
        return jobKeyStatusService;
    }

    public List<GroupTaskEntity> getJobKeyList() {
        return jobKeyList;
    }

    public ArgumentService getArgumentService() {
        return argumentService;
    }

    public void setSuiteRunning(String suiteKey) {
        suiteKeyStatusService.setSuiteRunning(suiteKey);
    }

    public void setSuiteCompleted(String suiteKey) {
        suiteKeyStatusService.setSuiteCompleted(suiteKey);
    }

    public void setSuiteError(String suiteKey) {
        suiteKeyStatusService.setSuiteError(suiteKey);
    }

    public void setJobRunning(String jobId) {
        jobKeyStatusService.setJobRunning(jobId);
    }

    public void setJobError(String jobId) {
        jobKeyStatusService.setJobError(jobId);
    }

    public boolean hasError() {
        return jobKeyStatusService.hasError();
    }
}
