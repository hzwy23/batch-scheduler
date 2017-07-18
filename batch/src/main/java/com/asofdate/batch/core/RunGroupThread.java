package com.asofdate.batch.core;

import com.asofdate.utils.JoinCode;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/29.
 */
public class RunGroupThread extends Thread {
    private final Logger logger = LoggerFactory.getLogger(RunGroupThread.class);
    private Scheduler scheduler;
    private ResourceManagement drm;
    private String suiteKey;

    public RunGroupThread(Scheduler scheduler,
                          ResourceManagement drm,
                          String suiteKey) {
        this.scheduler = scheduler;
        this.drm = drm;
        this.suiteKey = suiteKey;
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
            Set<String> jobKeySet = drm.getRunnableJob(suiteKey);

            for (String jobKey : jobKeySet) {
                // 获取批次中任务唯一标识key
                String jobId = JoinCode.join(suiteKey, jobKey);
                try {
                    // 修改任务状态，切换成启动状态
                    drm.setJobRunning(jobId);
                    // 启动任务触发器，启动任务
                    scheduler.triggerJob(JobKey.jobKey(jobId));
                } catch (SchedulerException e) {
                    // 设置任务状态为错误
                    drm.setJobError(jobId);
                    logger.info("启动任务失败，任务key是：{}", jobId);
                    logger.info(e.getMessage());
                }
            }

            if (drm.isSuiteCompleted(suiteKey)) {
                drm.setSuiteCompleted(suiteKey);
                break;
            }

            if (drm.hasError()) {
                drm.setSuiteError(suiteKey);
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
