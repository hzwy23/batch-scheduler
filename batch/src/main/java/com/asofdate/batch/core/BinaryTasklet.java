package com.asofdate.batch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by hzwy23 on 2017/5/31.
 */
public class BinaryTasklet implements Tasklet {
    private final String END_FLAG = "HZWY23-ASOFDATE-ETL-CMDSCRIPT-END";
    private final Logger logger = LoggerFactory.getLogger(CmdTasklet.class);
    private String ExitCode = "ExitCode=";
    private String ExitMsg = "ExitMsg=";
    private String scritpFile;

    public BinaryTasklet(String scriptFile, String basePath) {
        this.scritpFile = Paths.get(basePath, scriptFile).toString();
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

        try {
            process = Runtime.getRuntime().exec(this.scritpFile + " " + jobParameters);
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                line = line.replaceAll("\"|\'", "");

                if (END_FLAG.equals(line)) {
                    logger.info(jobName + " job execute completed.");
                    break;
                }

                if (line.indexOf(ExitCode) > -1) {
                    if (line.equals("ExitCode=0")) {
                        chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.COMPLETED);
                    } else {
                        chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.FAILED);
                    }
                }
            }
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
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
