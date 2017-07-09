package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.GroupDependencyDao;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.batch.service.GroupDependencyService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Service
@Scope("prototype")
public class GroupDependencyServiceImpl implements GroupDependencyService {
    @Autowired
    private GroupDependencyDao groupDependencyDao;
    /*
    * key : 任务组
    * value: 所有依赖的任务组
    * */
    private Map<String, Set<GroupDependencyEntity>> groupMap;

    @Override
    public List<GroupDependencyEntity> findById(String domainId, String batchId) {
        return groupDependencyDao.findById(domainId, batchId);
    }

    /*
    * 初始化任务组的依赖关系
    * */
    @Override
    public void afterPropertiesSet(String domainId, String batchId) {
        this.groupMap = groupMap = new HashMap<>();
        /*
        * 初始化批次中任务组的依赖HashMap
        * */
        for (GroupDependencyEntity m : findById(domainId, batchId)) {
            if (this.groupMap.containsKey(m.getId())) {
                this.groupMap.get(m.getId()).add(m);
            } else {
                Set<GroupDependencyEntity> set = new HashSet<>();
                set.add(m);
                this.groupMap.put(m.getId(), set);
            }
        }
    }

    /*
    * 获取任务组的依赖任务组列表
    * @param String gid 表示任务组id
    * */
    @Override
    public Set<GroupDependencyEntity> getGroupDependency(String gid) {
        return this.groupMap.get(gid);
    }

    @Override
    public List<BatchGroupEntity> getUp(String id) {
        return groupDependencyDao.getGroupDependency(id);
    }

    @Override
    public RetMsg deleteGroupDependency(String uuid) {
        try {
            int size = groupDependencyDao.deleteGroupDependency(uuid);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组依赖关系失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), uuid);
        }
    }

    @Override
    public RetMsg addGroupDependency(List<GroupDependencyEntity> list) {
        try {
            int size = groupDependencyDao.addGroupDependency(list);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "给任务组添加依赖失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
