package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import com.asofdate.batch.service.ExecService;
import com.asofdate.utils.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzwy23 on 2017/7/4.
 */
public class ExecTasklet implements Tasklet {
    private final Logger logger = LoggerFactory.getLogger(ExecTasklet.class);

    private String cmd = null;
    private String batchId = null;
    private String asOfDate = null;
    private ExecService execService;

    public ExecTasklet(String cmd, ExecService execService, BatchRunConfDto conf) {
        this.cmd = cmd;
        this.execService = execService;
        this.batchId = conf.getBatchId();
        this.asOfDate = conf.getAsOfDate();
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Object parametersObj = chunkContext.getStepContext().getJobParameters().get("JobParameters");
        String jobParameters = String.valueOf(System.currentTimeMillis());
        if (parametersObj != null) {
            jobParameters = parametersObj.toString();
        }

        String jobName = chunkContext.getStepContext().getJobName();

        Process process = null;

        BufferedReader input = null;

        String line = null;
        int idx = 1;
        try {
            process = Runtime.getRuntime().exec(cmd + " " + jobParameters);
            input = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            while ((line = input.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                writeLog(jobName, line, idx++);
            }
            process.waitFor();
        } catch (IOException e) {
            writeLog(jobName, e.getMessage(), idx++);
            logger.error(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return RepeatStatus.FINISHED;
    }

    private void writeLog(String jobId, String message, int idx) {
        ExecLogEntity row = new ExecLogEntity();
        row.setJobId(jobId);
        row.setAsOfDate(asOfDate);
        row.setBatchId(batchId);
        row.setMessage(message);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        row.setExecTime(sdf.format(new Date()));
        row.setSortId(idx);
        RetMsg retMsg = execService.echo(row);
        if (!retMsg.checkCode()) {
            logger.error(retMsg.toString());
        }
    }
}
