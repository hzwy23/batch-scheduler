package com.asofdate.batch.dao;

import com.asofdate.batch.entity.GroupDependencyEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface GroupDependencyDao {
    List<GroupDependencyEntity> findById(String domainId, String batchId);
}
