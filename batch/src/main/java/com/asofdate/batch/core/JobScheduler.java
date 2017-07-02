package com.asofdate.batch.core;

import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.service.ArgumentService;
import com.asofdate.batch.service.BatchDefineService;
import com.asofdate.batch.service.GroupStatusService;
import com.asofdate.batch.service.TaskStatusService;
import com.asofdate.batch.utils.BatchStatus;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/25.
 */
@Component
@Scope("prototype")
public class JobScheduler extends Thread {
    private final String BATCH_SUCCESS_MSG = "success";
    private final String BATCH_ERROR_MSG = "Running error";
    private final String BATCH_STOPPED_MSG = "stopped";
    private final Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    @Autowired
    private ArgumentService argumentService;
    // 批次运行对象
    private SchedulerFactoryBean scheduler;
    private QuartzConfiguration quartzConfiguration;

    private String domainId;
    private String batchId;
    private TaskStatusService taskStatus;
    private GroupStatusService groupStatus;
    private BatchDefineService batchDefineService;

    public void createJobSchedulerService(QuartzConfiguration quartzConfiguration,
                                          GroupStatusService groupStatus,
                                          TaskStatusService taskStatus,
                                          BatchDefineService batchDefineService) {
        this.batchId = quartzConfiguration.getBatchId();
        this.domainId = quartzConfiguration.getDomainId();
        this.quartzConfiguration = quartzConfiguration;
        this.scheduler = quartzConfiguration.getSchedulerFactoryBean();
        this.groupStatus = groupStatus;
        this.taskStatus = taskStatus;
        this.batchDefineService = batchDefineService;
    }

    @Override
    public void run() {
        if (domainId == null) {
            logger.error("domain id is empty. scheduler abort.");
            return;
        }

        if (batchId == null) {
            logger.error("batch id is null. scheduler abort.");
            return;
        }

        try {
            while (scheduler.isRunning()) {
                Map<String, BatchGroupEntity> map = groupStatus.getRunnableGroups();
                for (BatchGroupEntity m : map.values()) {
                    groupStatus.setGroupRunning(m.getUuid());
                    new RunGroupThread(scheduler.getScheduler(),
                            taskStatus,
                            groupStatus,
                            m.getUuid(),
                            m.getGroupId()).start();
                }

                if (taskStatus.isBatchCompleted()) {
                    logger.info("batch completed.");
                    logger.info("stop scheduler.");
                    scheduler.stop();
                    scheduler.destroy();
                    batchDefineService.destoryBatch(batchId, BATCH_SUCCESS_MSG, BatchStatus.BATCH_STATUS_COMPLETED);
                    batchDefineService.saveHistory(batchId);
                    batchPagging();
                    return;
                }

                /*
                * 检查批次中是否有错误任务
                * 一旦出现任务执行错误,则停止批次
                * 并撤销整个批次
                * */
                if (taskStatus.isError()) {
                    logger.info("task error, 销毁批次");
                    scheduler.stop();
                    scheduler.destroy();
                    batchDefineService.destoryBatch(batchId, BATCH_ERROR_MSG, BatchStatus.BATCH_STATUS_ERROR);
                    batchDefineService.saveHistory(batchId);
                    return;
                }

                Thread.sleep(100);
                if (BatchStatus.BATCH_STATUS_RUNNING != batchDefineService.getStatus(batchId)) {
                    logger.info("batch status is not running,batchid is:{},stauts is : {}", batchId, batchDefineService.getStatus(batchId));
                    scheduler.stop();
                    scheduler.destroy();
                    batchDefineService.destoryBatch(batchId, BATCH_STOPPED_MSG, BatchStatus.BATCH_STATUS_STOPPED);
                    batchDefineService.saveHistory(batchId);
                    return;
                }
            }
        } catch (InterruptedException e) {
            batchDefineService.destoryBatch(batchId, e.getMessage(), BatchStatus.BATCH_STATUS_ERROR);
            scheduler.stop();
            batchDefineService.saveHistory(batchId);
            e.printStackTrace();
        } catch (SchedulerException e) {
            batchDefineService.destoryBatch(batchId, e.getMessage(), BatchStatus.BATCH_STATUS_ERROR);
            scheduler.stop();
            batchDefineService.saveHistory(batchId);
            e.printStackTrace();
        } catch (Exception e) {
            batchDefineService.destoryBatch(batchId, e.getMessage(), BatchStatus.BATCH_STATUS_ERROR);
            scheduler.stop();
            batchDefineService.saveHistory(batchId);
            e.printStackTrace();
        }
    }

    private void batchPagging() throws Exception {
        RetMsg retMsg = batchDefineService.batchPagging(batchId);
        if (SysStatus.SUCCESS_CODE != retMsg.getCode()) {
            logger.info("批次已经运行到终止日期,终止运行");
            return;
        }
        groupStatus.afterPropertiesSet(domainId, batchId);
        taskStatus.afterPropertiesSet(domainId, batchId);
        argumentService.afterPropertySet(domainId, batchId);
        quartzConfiguration.createSchedulerFactoryBean(domainId, batchId, taskStatus, argumentService);
        this.scheduler = quartzConfiguration.getSchedulerFactoryBean();
        run();
    }
}
