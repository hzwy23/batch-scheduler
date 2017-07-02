package com.asofdate.batch.core;

import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.service.GroupStatusService;
import com.asofdate.batch.service.TaskStatusService;
import com.asofdate.utils.JoinCode;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/29.
 */
public class RunGroupThread extends Thread {
    private final Logger logger = LoggerFactory.getLogger(RunGroupThread.class);
    private Scheduler scheduler;
    private TaskStatusService taskStatus;
    private GroupStatusService groupStatus;
    private String gid;
    private String groupId;

    public RunGroupThread(Scheduler scheduler,
                          TaskStatusService taskStatusService,
                          GroupStatusService groupStatusService,
                          String gid, String groupId) {
        this.scheduler = scheduler;
        this.taskStatus = taskStatusService;
        this.groupStatus = groupStatusService;
        this.gid = gid;
        this.groupId = groupId;
    }

    @Override
    public void run() {
        /*
        * 将任务组设置成运行中
        * 根据任务组中的任务之间的依赖关系
        * 挨个执行这个任务组中的任务
        * 当这个任务组中的所有任务执行完成之后,设置任务组为完成状态
        * */
        while (true) {
            Map<String, GroupTaskEntity> taskMap = taskStatus.getRunnableTasks(gid, groupId);
            for (GroupTaskEntity mt : taskMap.values()) {
                try {
                    taskStatus.setTaskRunning(JoinCode.join(gid, mt.getUuid()));
                    scheduler.triggerJob(JobKey.jobKey(JoinCode.join(gid, mt.getUuid())));
                } catch (SchedulerException e) {
                    logger.info(e.getMessage());
                    e.printStackTrace();
                }
            }

            if (taskStatus.isGroupCompleted(gid, groupId)) {
                groupStatus.setGroupCompleted(gid);
                break;
            }

            if (taskStatus.isError()) {
                groupStatus.setGroupError(gid);
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
