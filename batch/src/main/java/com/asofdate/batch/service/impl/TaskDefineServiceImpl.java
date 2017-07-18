package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.dao.TaskDefineDao;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.entity.TaskDefineEntity;
import com.asofdate.batch.service.GroupTaskService;
import com.asofdate.batch.service.TaskDefineService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Service
public class TaskDefineServiceImpl implements TaskDefineService {
    @Autowired
    private TaskDefineDao dispatchTaskDefineDao;

    @Autowired
    private GroupTaskService groupTaskService;

    @Autowired
    private TaskArgumentDao taskArgumentDao;

    @Override
    public List<TaskDefineEntity> findAll(String domainId, String batchId) {
        List<TaskDefineEntity> list = dispatchTaskDefineDao.findAll(domainId);
        List<GroupTaskEntity> groupTaskEntityList = groupTaskService.findByBatchId(domainId, batchId);

        Set<String> set = new HashSet<String>();

        for (GroupTaskEntity m : groupTaskEntityList) {
            set.add(m.getTaskId());
        }

        for (int i = 0; i < list.size(); i++) {
            String taskId = list.get(i).getTaskId();
            if (!set.contains(taskId)) {
                list.remove(i);
                i--;
            }
        }

        return list;
    }

    @Override
    public List<TaskDefineEntity> getAll(String domainId) {
        return dispatchTaskDefineDao.findAll(domainId);
    }

    @Override
    public RetMsg add(TaskDefineEntity m) {
        try {
            int size = dispatchTaskDefineDao.add(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg delete(List<TaskDefineEntity> m) {
        try {
            String msg = dispatchTaskDefineDao.delete(m);
            if ("success".equals(msg)) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg update(TaskDefineEntity m) {
        try {
            int size = dispatchTaskDefineDao.update(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public List<TaskArgumentEntity> getTaskArg(String taskId) {
        return taskArgumentDao.getTaskArg(taskId);
    }

    @Override
    public RetMsg updateArgumentSort(String sortId, String uuid) {
        try {
            int size = taskArgumentDao.updateSort(sortId, uuid);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg deleteArg(String uuid) {
        try {
            int size = taskArgumentDao.deleteArg(uuid);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public TaskArgumentEntity getArgType(String argId) {
        return taskArgumentDao.getArgType(argId);
    }

    @Override
    public RetMsg addArg(TaskArgumentEntity taskArgumentEntity) {
        try {
            int size = taskArgumentDao.addArg(taskArgumentEntity);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg updateArgValue(String argValue, String uuid) {
        try {
            int size = taskArgumentDao.updateArgValue(argValue, uuid);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
