package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.GroupArgumentDao;
import com.asofdate.batch.dao.GroupTaskDao;
import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.batch.service.GroupTaskService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public JSONArray getTask(String groupId) {
        return groupTaskDao.getTask(groupId);
    }

    @Override
    public JSONArray getTaskArg(String id) {
        JSONArray ret = new JSONArray();
        String taskId = groupTaskDao.getTaskId(id);
        logger.info("task id is :" + taskId);
        JSONArray jsonArray = groupArgumentDao.getGroupArg(id);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            map.put(jsonObject.getString("arg_id"), jsonObject.getString("arg_value"));
        }
        logger.info("group arg is :" + jsonArray.toString());
        JSONArray taskArgList = taskArgumentDao.getTaskArg(taskId);
        logger.info("task arg  is :" + taskArgList.toString());
        for (int i = 0; i < taskArgList.length(); i++) {
            JSONObject one = (JSONObject) taskArgList.get(i);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uuid", one.getString("uuid"));
            jsonObject.put("task_id", one.getString("task_id"));
            jsonObject.put("arg_id", one.getString("arg_id"));
            switch (one.getString("arg_type")) {
                case "1":
                case "2":
                    jsonObject.put("arg_value", one.getString("arg_value"));
                    break;
                case "3":
                    if (map.containsKey(one.getString("arg_id"))) {
                        String argValue = map.get(one.getString("arg_id"));
                        jsonObject.put("arg_value", argValue);
                        break;
                    }
                default:
                    jsonObject.put("arg_value", "-");
            }

            jsonObject.put("sort_id", one.getString("sort_id"));
            jsonObject.put("domain_id", one.getString("domain_id"));
            jsonObject.put("arg_type", one.getString("arg_type"));
            jsonObject.put("arg_type_desc", one.getString("arg_type_desc"));
            jsonObject.put("arg_desc", one.getString("arg_desc"));
            jsonObject.put("code_number", one.getString("code_number"));
            ret.put(jsonObject);
        }
        return ret;
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
    public RetMsg addGroupArg(JSONArray jsonArray) {
        try {
            int size = groupTaskDao.addArg(jsonArray);
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
