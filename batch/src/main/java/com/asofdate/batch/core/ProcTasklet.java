package com.asofdate.batch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by hzwy23 on 2017/5/31.
 */
public class ProcTasklet implements Tasklet {
    private final Logger logger = LoggerFactory.getLogger(ProcTasklet.class);

    private JdbcTemplate jdbcTemplate;
    private String scriptFile = null;

    public ProcTasklet(String scriptFile, JdbcTemplate jdbcTemplate) {
        this.scriptFile = scriptFile;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        Object parametersObj = chunkContext.getStepContext().getJobParameters().get("JobParameters");
        String jobParameters = String.valueOf(System.currentTimeMillis());
        String params = "(";
        if (parametersObj != null) {
            jobParameters = parametersObj.toString();
            String[] p = jobParameters.split(" ");
            for (int i = 0; i < p.length; i++) {
                if (i == 0) {
                    params += "'" + p[i] + "'";
                } else {
                    params += ",'" + p[i] + "'";
                }
            }
        }
        params += ")";
        try {
            logger.info("program is :" + scriptFile + ", argument is:" + params);
            jdbcTemplate.execute("call " + scriptFile + params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.FAILED);
        }
        return RepeatStatus.FINISHED;
    }
}
