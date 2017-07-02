package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchGroupStatusDao;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.batch.service.GroupDependencyService;
import com.asofdate.batch.service.GroupStatusService;
import com.asofdate.batch.utils.GroupStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/28.
 */
@Service
@Scope("prototype")
public class GroupStatusServiceImpl implements GroupStatusService {
    @Autowired
    private BatchGroupService groupService;
    @Autowired
    private GroupDependencyService groupDependencyService;
    @Autowired
    private BatchGroupStatusDao batchGroupStatusDao;

    private String domainId;
    private String batchId;

    private List<BatchGroupEntity> groupList;
    /*
    * 批次中任务组的状态管理
    * key : 表示批次中的任务组
    * value: 表示任务组的状态信息
    *      0: 初始化
    *      1: 运行中
    *      2: 已完成
    *      3: 错误
    *      4: 停止
    * */
    private Map<String, Integer> groupMap;

    // 初始化两个变量
    public void afterPropertiesSet(String domainId, String batchId) {
        this.domainId = domainId;
        this.batchId = batchId;
        this.groupMap = new HashMap<String, Integer>();
        this.groupList = groupService.findByBatchId(domainId, batchId);

        /*
        * 初始化全部任务组
        * */
        for (BatchGroupEntity m : this.groupList) {
            // 0 表示初始化
            groupMap.put(m.getUuid(), GroupStatus.Gid_STATUS_INIT);
        }

        // 初始化任务组,状态信息表
        batchGroupStatusDao.init(batchId, groupMap);

        /*
        * 初始化注入对象
        * 任务组依赖关系初始化
        * */
        groupDependencyService.afterPropertiesSet(domainId, batchId);
    }


    /*
    * @return: Map<Key,Value>
    *     key: 表示批次中配置的需要运行的任务组id, 这个id不是任务组编码,而是配置任务组依赖关系时,生成的随机唯一性编码
    *     value: 是任务组的详细信息,包括任务组编码,所属域等等
    * */
    public Map getRunnableGroups() {
        Map<String, BatchGroupEntity> map = new HashMap<String, BatchGroupEntity>();
        for (BatchGroupEntity m : groupList) {
            map.remove(m.getUuid());
            /*
            * 如果任务组状态不是初始化值
            * 表示任务组已经被启动, 则不允许在此加入预备运行状态中.
            * */
            if (getGroupStatus(m.getUuid()) != GroupStatus.Gid_STATUS_INIT) {
                continue;
            }

            if (isGroupRunable(m.getUuid())) {
                map.put(m.getUuid(), m);
            }
        }
        return map;
    }

    /*
    * 设置任务组运行中
    * @param String gid 表示任务组id
    * */
    public void setGroupRunning(String gid) {
        groupMap.put(gid, GroupStatus.Gid_STATUS_RUNNING);
        batchGroupStatusDao.setGroupRunning(batchId, gid, GroupStatus.Gid_STATUS_RUNNING);
    }

    /*
    * 设置任务组已经运行完成
    * @param String gid 表示任务组id
    * */
    public void setGroupCompleted(String gid) {
        groupMap.put(gid, GroupStatus.Gid_STATUS_COMPLETED);
        batchGroupStatusDao.setGroupEnd(batchId, gid, GroupStatus.Gid_STATUS_COMPLETED);
    }

    @Override
    public void setGroupError(String gid) {
        groupMap.put(gid, GroupStatus.Gid_STATUS_ERROR);
        batchGroupStatusDao.setGroupEnd(batchId, gid, GroupStatus.Gid_STATUS_ERROR);
    }


    /*
    * 获取任务组状态
    * @param String gid 表示任务组gid
    * */
    public int getGroupStatus(String gid) {
        if (groupMap.containsKey(gid)) {
            return groupMap.get(gid).intValue();
        }
        return GroupStatus.Gid_STATUS_ERROR;
    }

    /*
   * 判断任务组是否可以转换成执行状态
   * 只有当任务组处于初始化状态,且其依赖的上级任务组已经运行完成
   * 那么才能将这个任务组设置成执行状态
   * @param String id 表示任务组的id
   * */
    public boolean isGroupRunable(String gid) {
        Set<GroupDependencyEntity> groupDependencyEntities = groupDependencyService.getGroupDependency(gid);
        if (groupDependencyEntities == null) {
            return true;
        }
        for (GroupDependencyEntity gp : groupDependencyEntities) {
            int statusCd = getGroupStatus(gp.getUpId());
            if (GroupStatus.Gid_STATUS_COMPLETED == statusCd) {
                continue;
            }
            return false;
        }
        return true;
    }
}
