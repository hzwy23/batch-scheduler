package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.TaskDependencyDao;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskDependencyEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.batch.service.TaskDependencyService;
import com.asofdate.utils.JoinCode;
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
public class TaskDependencyServiceImpl implements TaskDependencyService {
    @Autowired
    private TaskDependencyDao taskDependencyDao;
    @Autowired
    private BatchGroupService groupService;
    /*
    * key: 任务编码
    * value: 所有依赖的任务
    * */
    private Map<String, Set<String>> taskMap;

    @Override
    public List<TaskDependencyEntity> findById(String domainId, String batchId) {
        return taskDependencyDao.findById(domainId, batchId);
    }

    public void afterPropertiesSet(String domainId, String batchId) {
        this.taskMap = new HashMap<>();
        List<BatchGroupEntity> groupList = groupService.findByBatchId(domainId, batchId);
        List<TaskDependencyEntity> taskList = findById(domainId, batchId);

        /*
        * 初始化任务组中的任务依赖关系
        * */
        for (BatchGroupEntity gt : groupList) {
            for (TaskDependencyEntity m : taskList) {
                String id = JoinCode.join(gt.getUuid(), m.getId());
                if (this.taskMap.containsKey(id)) {
                    this.taskMap.get(id).add(JoinCode.join(gt.getUuid(), m.getUpId()));
                } else {
                    Set<String> set = new HashSet<>();
                    set.add(JoinCode.join(gt.getUuid(), m.getUpId()));
                    this.taskMap.put(id, set);
                }
            }
        }
    }

    @Override
    public List<GroupTaskEntity> getTaskDependency(String id) {
        return taskDependencyDao.getTaskDependency(id);
    }

    @Override
    public List<GroupTaskEntity> getGroupTask(String groupId, String id) {
        return taskDependencyDao.getGroupTasks(groupId, id);
    }

    @Override
    public RetMsg addTaskDependency(List<TaskDependencyEntity> list) {
        try {
            int size = taskDependencyDao.addTaskDependency(list);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "添加任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg deleteTaskDependency(String uuid) {
        try {
            int size = taskDependencyDao.deleteTaskDependency(uuid);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务依赖失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }


    /*
    * 获取任务的依赖关系列表
    * @param String gid 表示任务组id
    * @param String id  表示任务id
    * */
    public Set<String> getTaskDependency(String gid, String id) {
        return this.taskMap.get(JoinCode.join(gid, id));
    }

}
