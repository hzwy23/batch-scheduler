package com.asofdate.batch.core;

import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.service.ArgumentService;
import com.asofdate.batch.service.JobKeyStatusService;
import com.asofdate.utils.JoinCode;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.UUID;

/**
 * Created by hzwy23 on 2017/5/21.
 */
public class QuartzJobLauncher extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(QuartzJobLauncher.class);
    private JobLauncher jobLauncher;
    private JobRegistry jobRegistry;
    private JobKeyStatusService jobKeyStatusService;
    private ArgumentService argumentService;
    private String jobName;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Job job = jobRegistry.getJob(jobName);
            JobExecution jobExecution = jobLauncher.run(job, getJobParameters());
            if (ExitStatus.COMPLETED.getExitCode().equals(jobExecution.getExitStatus().getExitCode())) {
                jobKeyStatusService.setJobCompleted(jobName);
            } else {
                jobKeyStatusService.setJobError(jobName);
            }
        } catch (NoSuchJobException e) {
            jobKeyStatusService.setJobError(jobName);
            logger.error("{}执行失败，失败原因是：{},异常类：{}", jobName, e.getMessage(), "NoSuchJobException");
        } catch (JobInstanceAlreadyCompleteException e) {
            jobKeyStatusService.setJobError(jobName);
            logger.error("{}执行失败，失败原因是：{},异常类：{}", jobName, e.getMessage(), "JobInstanceAlreadyCompleteException");
        } catch (JobExecutionAlreadyRunningException e) {
            jobKeyStatusService.setJobError(jobName);
            logger.error("{}执行失败，失败原因是：{},异常类：{}", jobName, e.getMessage(), "JobExecutionAlreadyRunningException");
        } catch (JobParametersInvalidException e) {
            jobKeyStatusService.setJobError(jobName);
            logger.error("{}执行失败，失败原因是：{},异常类：{}", jobName, e.getMessage(), "JobParametersInvalidException");
        } catch (JobRestartException e) {
            jobKeyStatusService.setJobError(jobName);
            logger.error("{}执行失败，失败原因是：{},异常类：{}", jobName, e.getMessage(), "JobRestartException");
        } finally {
            jobRegistry.unregister(jobName);
        }
    }

    public JobParameters getJobParameters() {
        JobParametersBuilder builder = new JobParametersBuilder();

        String jobId = JoinCode.getTaskCode(jobName);
        List<TaskArgumentEntity> list = argumentService.queryArgument(jobId);
        if (list == null) {
            builder.addString("uuid", UUID.randomUUID().toString());
            return builder.toJobParameters();
        }
        String jobParameters = "";
        for (TaskArgumentEntity m : list) {
            String val = m.getArgValue();
            if (val.contains(" ")) {
                val = "\"" + val + "\"";
            }
            jobParameters += " " + val;
        }
        builder.addString("JobParameters", jobParameters.trim());
        builder.addString("uuid", UUID.randomUUID().toString());
        return builder.toJobParameters();
    }

    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public JobRegistry getJobRegistry() {
        return jobRegistry;
    }

    public void setJobRegistry(JobRegistry jobRegistry) {
        this.jobRegistry = jobRegistry;
    }

    public JobKeyStatusService getJobKeyStatusService() {
        return jobKeyStatusService;
    }

    public void setJobKeyStatusService(JobKeyStatusService jobKeyStatusService) {
        this.jobKeyStatusService = jobKeyStatusService;
    }

    public ArgumentService getArgumentService() {
        return argumentService;
    }

    public void setArgumentService(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
