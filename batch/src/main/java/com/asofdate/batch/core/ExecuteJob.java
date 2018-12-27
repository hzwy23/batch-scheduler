package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.service.ArgumentService;
import com.asofdate.batch.service.ExecService;
import com.asofdate.batch.service.JobKeyStatusService;
import com.asofdate.batch.utils.DateTime;
import com.asofdate.utils.JoinCode;
import com.google.common.base.Charsets;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hzwy23 on 2017/7/4.
 */
public class ExecuteJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(ExecuteJob.class);

    private String scriptPath;
    private ExecService execService;
    private ArgumentService argumentService;
    private JobKeyStatusService jobKeyStatusService;
    private String jobName;
    private BatchRunConfDto conf;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String jobParameters = getJobParameters();
        String cmd = getScriptFilePath(conf.getBasePath() ,scriptPath);
        logger.info("开始执行时间是：{}, 脚本路径是：{}，参数是：{}", DateTime.getCurrentDateTime(), cmd, jobParameters);

        Process process = null;
        BufferedReader input = null;
        String line = null;
        int idx = 1;
        try {
            process = Runtime.getRuntime().exec(cmd + " " + jobParameters);
            input = new BufferedReader(new InputStreamReader(process.getInputStream(), Charsets.UTF_8));

            while ((line = input.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                writeLog(jobName, line, idx++);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            writeLog(jobName, e.getMessage(), idx++);
            logger.error(e.getMessage());
        } finally {
            jobKeyStatusService.setJobCompleted(jobName);
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private String getJobParameters() {
        String jobId = JoinCode.getTaskCode(jobName);
        List<TaskArgumentEntity> list = argumentService.queryArgument(jobId);
        if (list == null) {
            return "";
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
            scriptPath = scriptPath.replace("/","\\");
            return basePath + scriptPath;
        } else {
            // linux 系统
            scriptPath = scriptPath.replace("\\","/");
            return basePath + scriptPath;
        }
    }

    private void writeLog(String jobId, String message, int idx) {
        ExecLogEntity row = new ExecLogEntity();
        row.setJobId(jobId);
        row.setAsOfDate(conf.getAsOfDate());
        row.setBatchId(conf.getBatchId());
        row.setMessage(message);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        row.setExecTime(sdf.format(new Date()));
        row.setSortId(idx);
        execService.echo(row);
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public void setExecService(ExecService execService) {
        this.execService = execService;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setConf(BatchRunConfDto conf) {
        this.conf = conf;
    }

    public void setArgumentService(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    public void setJobKeyStatusService(JobKeyStatusService jobKeyStatusService) {
        this.jobKeyStatusService = jobKeyStatusService;
    }
}
