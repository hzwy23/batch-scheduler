package com.asofdate.batch.service;

import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/27.
 *
 * @author hzwy23
 */
public interface GroupDependencyService {
    /**
     * 使用spring自动注入bean后，调用这个方法初始化对象属性
     *
     * @param domainId
     * @param batchId
     */
    void afterPropertiesSet(String domainId, String batchId);

    /**
     * 根据域编码，批次编码查询任务组的依赖关系
     *
     * @param domainId
     * @param batchId
     */
    List<GroupDependencyEntity> findById(String domainId, String batchId);


    /**
     * 查询任务组的依赖关系
     *
     * @param gid 批次中任务组id
     */
    Set<GroupDependencyEntity> getGroupDependency(String gid);

    /**
     * 查询上级依赖任务
     *
     * @param id
     */
    List<BatchGroupEntity> getUp(String id);

    /**
     * 删除任务组依赖
     *
     * @param uuid 任务组依赖配置中唯一id
     */
    RetMsg deleteGroupDependency(String uuid);

    /**
     * 给任务组新增依赖
     */
    RetMsg addGroupDependency(List<GroupDependencyEntity> list);
}
