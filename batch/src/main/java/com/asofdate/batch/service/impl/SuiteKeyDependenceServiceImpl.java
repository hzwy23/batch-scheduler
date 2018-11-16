package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.GroupDependencyDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.batch.service.SuiteKeyDependenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Service
@Scope("prototype")
public class SuiteKeyDependenceServiceImpl implements SuiteKeyDependenceService {
    @Autowired
    private GroupDependencyDao groupDependencyDao;
    /*
     * key : 任务组
     * value: 所有依赖的任务组
     * */
    private Map<String, Set<GroupDependencyEntity>> suiteKeyDep;


    /*
     * 初始化任务组的依赖关系
     * */
    @Override
    public void afterPropertiesSet(BatchRunConfDto conf) {
        suiteKeyDep = new HashMap<>();

        /**
         * 初始化批次中任务组的依赖HashMap
         * */
        for (GroupDependencyEntity m : findById(conf.getDomainId(), conf.getBatchId())) {
            if (suiteKeyDep.containsKey(m.getSuiteKey())) {
                suiteKeyDep.get(m.getSuiteKey()).add(m);
            } else {
                Set<GroupDependencyEntity> set = new HashSet<>();
                set.add(m);
                suiteKeyDep.put(m.getSuiteKey(), set);
            }
        }
    }

    /**
     * 获取任务组的依赖任务组列表
     *
     * @param suiteKey 表示任务组id
     */
    @Override
    public Set<GroupDependencyEntity> getSuiteDependency(String suiteKey) {
        return suiteKeyDep.get(suiteKey);
    }

    private List<GroupDependencyEntity> findById(String domainId, String batchId) {
        return groupDependencyDao.findById(domainId, batchId);
    }
}
