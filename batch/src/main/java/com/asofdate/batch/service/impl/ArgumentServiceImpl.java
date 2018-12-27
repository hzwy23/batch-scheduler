package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.ArgumentDefineDao;
import com.asofdate.batch.dao.BatchArgumentDao;
import com.asofdate.batch.dao.GroupArgumentDao;
import com.asofdate.batch.dao.TaskArgumentDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.*;
import com.asofdate.batch.service.ArgumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hzwy23 on 2017/5/29.
 * <p>
 * 参数分为四种类型,分别是:
 * 1: 固定参数      这种类型的参数在参数定义时固定下来,在不同的任务中,值相同.
 * 2: 任务参数      这种类型的参数,在不同的任务中,可以自定义值.
 * 3: 任务组参数    这种类型的参数,允许任务呗任务组调用时,对参数值进行赋值.
 * 4: 批次参数      这种类型的参数,在批次运行时进行设定值,方便统一管理.
 */
@Service
@Scope("prototype")
public class ArgumentServiceImpl implements ArgumentService {

    // 参数类型定义
    private final String BATCH_ARGUMENT = "4";
    private final String GROUP_ARGUMENT = "3";
    private final String TASK_ARGUMENT = "2";
    private final String FIXED_ARGUMENT = "1";
    // 任务参数
    @Autowired
    private TaskArgumentDao taskArgumentDao;
    // 批次参数
    @Autowired
    private BatchArgumentDao batchArgumentDao;
    // 参数定义
    @Autowired
    private ArgumentDefineDao argumentDefineDao;
    // 任务组参数
    @Autowired
    private GroupArgumentDao groupArgumentDao;
    // 任务组中任务配置

    // 任务组中唯一id与任务映射关系
    private Map<String, GroupTaskEntity> groupTaskMap;
    // 参数定义列表,系统中所有已经定义的参数值
    private Map<String, ArgumentDefineEntity> argDefineMap;
    private Map<String, List<TaskArgumentEntity>> taskArgMap;
    /*
     *  任务组参数
     *  key 是任务组中配置任务时生成的具有唯一性的id
     *  alue 只这个id所绑定任务的参数列表,一个任务可以有多个参数
     *  上述这个map中的每一行,表示了这个任务组中,某一个配置的任务,包含的任务组类型的参数,
     *  如果没有任务类型的参数,则为空
     * */
    private Map<String, List<GroupArgumentEntity>> groupArgumentMap;

    /*
     * 初始化参数管理服务
     * 通过@Autowired自动注入这个类后,需要调用下边这个方法初始化类
     * */
    public void afterPropertySet(BatchRunConfDto conf, List<GroupTaskEntity> jobKeyList) {
        groupTaskMap = new HashMap<>();
        List<ArgumentDefineEntity> argDefineList = argumentDefineDao.findAll(conf.getDomainId());
        groupArgumentMap = new HashMap<>();

        // 这个id是任务组中配置任务时生成的id号,
        // 这个id号具有唯一性,一个id绑定一个具体的任务
        String jobKey = null;
        for (GroupArgumentEntity m : groupArgumentDao.findAll(conf.getDomainId())) {
            jobKey = m.getJobKey();
            if (groupArgumentMap.containsKey(jobKey)) {
                groupArgumentMap.get(jobKey).add(m);
            } else {
                List<GroupArgumentEntity> l = new ArrayList<>();
                l.add(m);
                groupArgumentMap.put(jobKey, l);
            }
        }

        for (GroupTaskEntity m : jobKeyList) {
            groupTaskMap.put(m.getJobKey(), m);
        }

        // 初始化域中所有的任务定义信息
        List<BatchArgumentEntiry> batchArgList = batchArgumentDao.findAll(conf.getDomainId(), conf.getBatchId());
        initArgDefineMap(batchArgList, argDefineList);
        List<TaskArgumentEntity> taskArgList = taskArgumentDao.findAll(conf.getDomainId());
        initTaskArgMap(taskArgList);
    }


    /*
     * 根据任务组中任务唯一编码获取其需要的参数
     * 参数分为三个级别
     * 1: 批次参数
     * 2: 任务组中配置任务的参数
     * 3: 固定参数
     * */
    @Override
    public List<TaskArgumentEntity> queryArgument(String id) {
        String taskId = groupTaskMap.get(id).getTaskId();
        if (taskId == null) {
            return null;
        }

        List<TaskArgumentEntity> list = taskArgMap.get(taskId);
        if (list == null) {
            return null;
        }

        CopyOnWriteArrayList<TaskArgumentEntity> copyOnWriteArrayList = new CopyOnWriteArrayList<TaskArgumentEntity>(list);


        for (TaskArgumentEntity m : copyOnWriteArrayList) {
            String argType = argDefineMap.get(m.getArgId()).getArgType();
            switch (argType) {
                case GROUP_ARGUMENT:
                    for (GroupArgumentEntity g : groupArgumentMap.get(id)) {
                        if (g.getArgId().equals(m.getArgId())) {
                            m.setArgValue(g.getArgValue());
                            break;
                        }
                    }
                    break;
                case FIXED_ARGUMENT:
                case BATCH_ARGUMENT:
                    String argValue = argDefineMap.get(m.getArgId()).getArgValue();
                    m.setArgValue(argValue);
                    break;
            }
        }

        Collections.sort(copyOnWriteArrayList, new Comparator<TaskArgumentEntity>() {
            @Override
            public int compare(TaskArgumentEntity o1, TaskArgumentEntity o2) {
                return Integer.parseInt(o1.getSortId()) - Integer.parseInt(o2.getSortId());
            }
        });

        return copyOnWriteArrayList;
    }

    private void initArgDefineMap(List<BatchArgumentEntiry> list, List<ArgumentDefineEntity> argDefineList) {
        String argType = null;
        String argId = null;
        String argValue = null;

        /*
         * map中存放的全部是批次参数信息
         * key :  arg_id
         * value : BatchArgumentEntiry
         * 同一个域中,批次编码唯一, 批次参数在这个批次中的值也是唯一
         * */
        Map<String, BatchArgumentEntiry> map = new HashMap<>();
        for (BatchArgumentEntiry m : list) {
            map.put(m.getArgId(), m);
        }

        // argDefineMap 变量中存放的是整个批次中所有的参数信息, 包括固定参数,任务参数,任务组参数,批次参数
        argDefineMap = new HashMap<>();

        for (ArgumentDefineEntity m : argDefineList) {
            // 如果是批次参数, 需要从map中取出批次参数的值
            if (BATCH_ARGUMENT.equals(m.getArgType())) {
                argId = m.getArgId();
                if (map.containsKey(argId)) {
                    argValue = map.get(argId).getArgValue();
                    m.setArgValue(argValue);
                }
            }

            /*
             * 如果参数类型不是批次参数,
             * 如果是固定参数,则固定参数值在参数定义中已经赋值
             * 如果是任务参数或者任务组参数,则在后边进行赋值
             * 这个函数中,只完成了固定参数,批次参数的赋值
             * */
            argDefineMap.put(m.getArgId(), m);
        }
    }

    /*
     * 初始化任务参数
     * 任务参数的值与任务绑定,在参数定义中,同一个参数,如果类型是任务参数
     * 这个参数,在不同的任务中,值可以不同,任务参数的key是task_id, value是一个List
     * 一个任务,可以有多个参数,所以value是List
     * */
    private void initTaskArgMap(List<TaskArgumentEntity> list) {
        taskArgMap = new HashMap<>();
        for (TaskArgumentEntity m : list) {
            if (taskArgMap.containsKey(m.getTaskId())) {
                taskArgMap.get(m.getTaskId()).add(m);
            } else {
                List<TaskArgumentEntity> l = new ArrayList<>();
                l.add(m);
                taskArgMap.put(m.getTaskId(), l);
            }
        }
    }
}
