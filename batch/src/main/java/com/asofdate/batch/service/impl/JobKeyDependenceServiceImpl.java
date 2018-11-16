package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.TaskDependencyDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.dto.JobKeyDepDto;
import com.asofdate.batch.service.JobKeyDependenceService;
import com.asofdate.utils.JoinCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Service
@Scope("prototype")
public class JobKeyDependenceServiceImpl implements JobKeyDependenceService {
    @Autowired
    private TaskDependencyDao taskDependencyDao;
    /*
     * key: 任务编码
     * value: 所有依赖的任务
     * */
    private Map<String, Set<String>> jobKeyDep;

    public void afterPropertiesSet(BatchRunConfDto conf) {
        this.jobKeyDep = new HashMap<>();
        List<JobKeyDepDto> taskList = findById(conf.getDomainId(), conf.getBatchId());

        /*
         * 初始化任务组中的任务依赖关系
         * */
        for (JobKeyDepDto m : taskList) {
            String id = JoinCode.join(m.getSuiteKey(), m.getJobKey());
            String upId = JoinCode.join(m.getSuiteKey(), m.getUpJobKey());
            if (jobKeyDep.containsKey(id)) {
                jobKeyDep.get(id).add(upId);
            } else {
                Set<String> set = new HashSet<>();
                set.add(upId);
                jobKeyDep.put(id, set);
            }
        }
    }


    /**
     * 获取任务的依赖关系列表
     *
     * @param suiteKey 表示任务组id
     * @param jobKey   表示任务id
     */
    public Set<String> getJobDependence(String suiteKey, String jobKey) {
        return jobKeyDep.get(JoinCode.join(suiteKey, jobKey));
    }

    private List<JobKeyDepDto> findById(String domainId, String batchId) {
        return taskDependencyDao.findById(domainId, batchId);
    }

}
