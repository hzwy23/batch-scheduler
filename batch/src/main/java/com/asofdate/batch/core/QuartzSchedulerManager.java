package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.service.BatchDefineService;
import com.asofdate.batch.utils.BatchStatus;
import com.asofdate.batch.utils.DateTime;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/25.
 */
@Component
@Scope("prototype")
public class QuartzSchedulerManager{
    private final String BATCH_SUCCESS_MSG = "success";
    private final String BATCH_ERROR_MSG = "Running error";
    private final String BATCH_STOPPED_MSG = "stopped";
    private final Logger logger = LoggerFactory.getLogger(QuartzSchedulerManager.class);
    @Autowired
    public QuartzSchedulerConfig quartzSchedulerConfig;
    @Autowired
    private BatchDefineService batchDefineService;
    @Autowired
    private ResourceManagement drm;

    private BatchRunConfDto conf;
    private SchedulerFactoryBean scheduler;

    // 创建调度服务
    public void createJobSchedulerService(BatchRunConfDto conf) throws Exception {
        this.conf = conf;
        drm.afterPropertiesSet(conf);
        logger.debug("初始化参数管理服务成功，批次号是：{}", conf.getBatchId());
        this.scheduler = quartzSchedulerConfig.createSchedulerFactoryBean(conf, drm);
    }

    @Async
    public void run() {
        while (schedulerCenter() == BatchStatus.BATCH_STATUS_COMPLETED) {
            RetMsg retMsg = batchPagging();
            if (!retMsg.checkCode()) {
                if (SysStatus.COMPLETED.equals(retMsg.getCode())) {
                    logger.info(retMsg.getMessage());
                } else {
                    logger.error(retMsg.getMessage());
                }
                return;
            }
        }
        logger.info("批次停止运行");
    }


    private int schedulerCenter() {
        try {
            while (scheduler.isRunning()) {
                /**
                 * 获取可以运行的任务组
                 * 一旦任务组依赖的上级任务组运行完成，
                 * 这个任务组将会被设置成执行态
                 * */
                Set<String> set = drm.getRunnableSuite();
                for (String suiteKey : set) {
                    drm.setSuiteRunning(suiteKey);
                    new RunGroupThread(scheduler.getScheduler(), drm, suiteKey).start();
                }

                /**
                 * 查询是否所有的任务运行完成
                 * 如果全部任务运行完成，则批次日期这天的批次与您
                 * 接着根据翻页频率，翻页执行批次
                 * */
                if (drm.isBatchCompleted()) {
                    logger.info("批次执行完成，完成时间是：{}, 批次号是：{}，批次日期是：{}", DateTime.getCurrentDateTime(), conf.getBatchId(), conf.getAsOfDate());
                    scheduler.stop();
                    scheduler.destroy();
                    batchDefineService.destoryBatch(conf.getBatchId(), BATCH_SUCCESS_MSG, BatchStatus.BATCH_STATUS_COMPLETED);
                    batchDefineService.saveHistory(conf.getBatchId());
                    return BatchStatus.BATCH_STATUS_COMPLETED;
                }

                /**
                 * 检查批次中是否有错误任务
                 * 一旦出现任务执行错误,则停止批次
                 * 并撤销整个批次
                 * */
                if (drm.hasError()) {
                    logger.info("任务执行失败，停止调度服务, 批次号是：{}", conf.getBatchId());
                    scheduler.stop();
                    scheduler.destroy();
                    batchDefineService.destoryBatch(conf.getBatchId(), BATCH_ERROR_MSG, BatchStatus.BATCH_STATUS_ERROR);
                    batchDefineService.saveHistory(conf.getBatchId());
                    return BatchStatus.BATCH_STATUS_ERROR;
                }

                Thread.sleep(1000);
                // 如果批次状态被设置为非执行状态，则退出当前批次
                int batchSt = batchDefineService.getStatus(conf.getBatchId());
                if (BatchStatus.BATCH_STATUS_RUNNING != batchSt) {
                    logger.info("批次处于非运行状态，退出调度服务。批次号是：{}, 状态是：{}", conf.getBatchId(), batchDefineService.getStatus(conf.getBatchId()));
                    scheduler.stop();
                    scheduler.destroy();
                    batchDefineService.destoryBatch(conf.getBatchId(), BATCH_STOPPED_MSG, BatchStatus.BATCH_STATUS_STOPPED);
                    batchDefineService.saveHistory(conf.getBatchId());
                    return batchSt;
                }
            }
            return batchDefineService.getStatus(conf.getBatchId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("批次运行异常,异常信息是：{}", e.getMessage());
            batchDefineService.destoryBatch(conf.getBatchId(), e.getMessage(), BatchStatus.BATCH_STATUS_ERROR);
            logger.info("停止批次{}", conf.getBatchId());
            scheduler.stop();
            logger.info("批次{}停止成功, 保存批次运行历史记录", conf.getBatchId());
            batchDefineService.saveHistory(conf.getBatchId());
            logger.info("批次历史记录保存完成，{}结束调度", conf.getBatchId());
            return BatchStatus.BATCH_STATUS_ERROR;
        }
    }


    private RetMsg batchPagging() {
        RetMsg retMsg = batchDefineService.batchPagging(conf);
        if (SysStatus.SUCCESS_CODE != retMsg.getCode()) {
            logger.info("批次【{}】已经运行到终止日期,终止运行", conf.getBatchId());
            return retMsg;
        }

        conf = batchDefineService.initConf(conf.getBatchId(), conf.getDomainId());
        drm.afterPropertiesSet(conf);
        try {
            scheduler = quartzSchedulerConfig.createSchedulerFactoryBean(conf, drm);
        } catch (Exception e) {
            e.printStackTrace();
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "初始化调度器失败", null);
        }
        return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
    }
}
