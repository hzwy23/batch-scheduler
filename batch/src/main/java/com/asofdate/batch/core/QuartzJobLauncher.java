package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.service.ArgumentService;
import com.asofdate.batch.service.ExecService;
import com.asofdate.batch.service.JobKeyStatusService;
import com.asofdate.utils.JoinCode;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/21.
 */
public class QuartzJobLauncher extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(QuartzJobLauncher.class);

    private JobKeyStatusService jobKeyStatusService;
    private ArgumentService argumentService;
    private String jobName;
    private int taskType;
    private String scriptPath;
    private ExecService execService;
    private BatchRunConfDto conf;

    // 任务在这个地方执行
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        jobKeyStatusService.setJobCompleted(jobName);
        logger.info("{}", jobExecutionContext);
        logger.info("job key status service is :{}", jobKeyStatusService);
        logger.info("argument service is : {}", argumentService);
        logger.info("job name is: {}", jobName);
        String param = getJobParameters();
        logger.info("job parameters is : {}", param);
        logger.info("task type is: {}", taskType);
        String basePath = conf.getBasePath();
        logger.info("base path is: {}", basePath);
        logger.info("script path is: {}", scriptPath);
        String cmd = getScriptFilePath(basePath, scriptPath);
        logger.info("script path is: {}", cmd);
        ExecTasklet execTasklet = new ExecTasklet(cmd,execService,param,jobName,conf);
        execTasklet.execute();
    }

    private String getJobParameters() {
        String jobId = JoinCode.getTaskCode(jobName);
        List<TaskArgumentEntity> list = argumentService.queryArgument(jobId);
        if (list == null) {
            return null;
        }
        StringBuilder jobParameters = new StringBuilder();
        for (TaskArgumentEntity m : list) {
            String val = m.getArgValue();
            jobParameters.append(" ").append("\"").append(val).append("\"");
        }
        return jobParameters.toString();
    }

    private String getScriptFilePath(String basePath, String scriptPath) {
        if ("\\".equals(File.separator)) {
            // window 系统
            String[] paths = scriptPath.split("/");
            if (paths.length != 0) {
                // 配置文件中采用了Linux格式，需要转换成window路径格式
                scriptPath = scriptPath.replace("/","\\");
            }
            return basePath + scriptPath;
        } else {
            // linux 系统
            String[] paths = scriptPath.split("\\");
            if (paths.length != 0) {
                // 配置文件中采用了Linux格式，需要转换成window路径格式
                scriptPath = scriptPath.replace("\\","/");
            }
            return basePath + scriptPath;
        }
    }

    public void setJobKeyStatusService(JobKeyStatusService jobKeyStatusService) { this.jobKeyStatusService = jobKeyStatusService; }
    public void setArgumentService(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public void setTaskType(int taskType){ this.taskType = taskType; }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public void setExecService(ExecService execService) {
        this.execService = execService;
    }

    public void setConf(BatchRunConfDto conf) {
        this.conf = conf;
    }
}
