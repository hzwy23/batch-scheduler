package com.asofdate.batch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hzwy23 on 2017/7/4.
 */
public class ExecTasklet implements Tasklet{
    private final Logger logger = LoggerFactory.getLogger(ExecTasklet.class);

    private String cmd = null;

    public ExecTasklet(String cmd){
        this.cmd = cmd;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Object parametersObj = chunkContext.getStepContext().getJobParameters().get("JobParameters");
        String jobParameters = String.valueOf(System.currentTimeMillis());
        if (parametersObj != null) {
            jobParameters = parametersObj.toString();
        }

//        String jobName = chunkContext.getStepContext().getJobName();

        Process process = null;

        BufferedReader input = null;

        String line = null;

        try {

            process = Runtime.getRuntime().exec(cmd + " " + jobParameters);

            input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = input.readLine()) != null) {
                logger.info(line);
            }

            process.waitFor();

        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return RepeatStatus.FINISHED;
    }
}
