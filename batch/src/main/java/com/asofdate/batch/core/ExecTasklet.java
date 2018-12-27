package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import com.asofdate.batch.service.ExecService;
import com.asofdate.utils.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzwy23 on 2017/7/4.
 */
public class ExecTasklet {
    private final Logger logger = LoggerFactory.getLogger(ExecTasklet.class);

    private String cmd = null;
    private String batchId = null;
    private String asOfDate = null;
    private ExecService execService;
    private String jobParameters;
    private String jobName;

    public ExecTasklet(String cmd, ExecService execService,String jobParameters, String jobName, BatchRunConfDto conf) {
        this.cmd = cmd;
        this.execService = execService;
        this.jobParameters = jobParameters;
        this.jobName = jobName;
        this.batchId = conf.getBatchId();
        this.asOfDate = conf.getAsOfDate();
    }

    public boolean execute() {
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
        } catch (IOException | InterruptedException e) {
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
        return true;
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
        execService.echo(row);
    }
}
