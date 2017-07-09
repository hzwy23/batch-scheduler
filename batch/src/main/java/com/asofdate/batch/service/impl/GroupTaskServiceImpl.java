package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.GroupArgumentDao;
import com.asofdate.batch.dao.GroupTaskDao;
import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.dto.GroupDefineDto;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupArgumentEntity;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.batch.service.GroupTaskService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/25.
 */
@Service
public class GroupTaskServiceImpl implements GroupTaskService {
    private final Logger logger = LoggerFactory.getLogger(GroupTaskServiceImpl.class);
    @Autowired
    private GroupTaskDao groupTaskDao;

    @Autowired
    private BatchGroupService batchGroupService;

    @Autowired
    private TaskArgumentDao taskArgumentDao;

    @Autowired
    private GroupArgumentDao groupArgumentDao;

    @Override
    public List<GroupTaskEntity> findByBatchId(String domainId, String batchId) {
        List<GroupTaskEntity> list = groupTaskDao.findAll(domainId);

        List<BatchGroupEntity> batchGroupEntityList = batchGroupService.findByBatchId(domainId, batchId);
        Map<String, BatchGroupEntity> map = new HashMap<String, BatchGroupEntity>();
        for (BatchGroupEntity m : batchGroupEntityList) {
            if (!map.containsKey(m.getGroupId())) {
                map.put(m.getGroupId(), m);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (!map.containsKey(list.get(i).getGroupId())) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Override
    public List<GroupTaskEntity> getTask(String groupId) {
        return groupTaskDao.getTask(groupId);
    }

    @Override
    public List<TaskArgumentEntity> getTaskArg(String id) {

        String taskId = groupTaskDao.getTaskId(id);

        logger.debug("task id is :" + taskId);
        List<GroupArgumentEntity> groupArg = groupArgumentDao.getGroupArg(id);

        Map<String, String> map = new HashMap<>();
        for (GroupArgumentEntity m : groupArg) {
            map.put(m.getArgId(), m.getArgValue());
        }

        List<TaskArgumentEntity> taskArgList = taskArgumentDao.getTaskArg(taskId);
        logger.debug("task arg  is :" + taskArgList.toString());

        for (TaskArgumentEntity m : taskArgList) {

            switch (m.getArgType()) {
                case "1":
                case "2":
                    break;
                case "3":
                    if (map.containsKey(m.getArgId())) {
                        String argValue = map.get(m.getArgId());
                        m.setArgValue(argValue);
                        break;
                    }
                default:
                    m.setArgValue("-");
            }
        }
        return taskArgList;
    }

    @Override
    public RetMsg deleteTask(String id) {
        try {
            int size = groupTaskDao.deleteTask(id);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组中任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg addTask(String id, String groupId, String taskId, String domainId) {
        try {
            int size = groupTaskDao.addTask(id, groupId, taskId, domainId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "在任务组中添加任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg addGroupArg(List<GroupDefineDto> list) {
        try {
            int size = groupTaskDao.addArg(list);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "任务组类型参数设定值失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg deleteTask(Set<String> args) {
        try {
            int size = groupTaskDao.deleteTask(args);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组中任务信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
