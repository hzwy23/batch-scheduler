package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.GroupDependencyEntity;

import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/27.
 *
 * @author hzwy23
 */
public interface SuiteKeyDependenceService {
    /**
     * 使用spring自动注入bean后，调用这个方法初始化对象属性
     *
     * @param conf
     */
    void afterPropertiesSet(BatchRunConfDto conf);

    /**
     * 查询任务组的依赖关系
     *
     * @param gid 批次中任务组id
     */
    Set<GroupDependencyEntity> getSuiteDependency(String gid);

}
