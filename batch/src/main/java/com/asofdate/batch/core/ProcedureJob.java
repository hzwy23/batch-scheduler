package com.asofdate.batch.core;

import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.batch.service.ArgumentService;
import com.asofdate.batch.service.JobKeyStatusService;
import com.asofdate.batch.utils.DateTime;
import com.asofdate.utils.JoinCode;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/31.
 */
public class ProcedureJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(ProcedureJob.class);

    private JdbcTemplate jdbcTemplate;
    private String scriptPath;
    private ArgumentService argumentService;
    private JobKeyStatusService jobKeyStatusService;
    private String jobName;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobParameters = getJobParameters();
        try {
            logger.info("开始执行时间是：{}, 存储过程是：{}, 参数是：{}" , DateTime.getCurrentDateTime(), scriptPath, jobParameters);
            jdbcTemplate.execute("call " + scriptPath + jobParameters);
            jobKeyStatusService.setJobCompleted(jobName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private String getJobParameters() {
        String jobId = JoinCode.getTaskCode(jobName);
        List<TaskArgumentEntity> list = argumentService.queryArgument(jobId);
        if (list == null) {
            return "()";
        }
        StringBuilder params = new StringBuilder("(");
        for(TaskArgumentEntity ele : list){
            params.append("'").append(ele.getArgValue()).append("',");
        }
        if (params.length() > 1) {
            params.setCharAt(params.length()-1,')');
        } else {
            params.append(')');
        }
        return params.toString();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public void setArgumentService(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    public void setJobKeyStatusService(JobKeyStatusService jobKeyStatusService) {
        this.jobKeyStatusService = jobKeyStatusService;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
