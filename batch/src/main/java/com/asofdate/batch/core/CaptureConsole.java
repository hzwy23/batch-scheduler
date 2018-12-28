package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import com.asofdate.batch.service.ExecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CaptureConsole {

    private final Logger logger = LoggerFactory.getLogger(CaptureConsole.class);
    @Autowired
    private ExecService execService;

    @Async
    public Future capture(Process process, String jobName, BatchRunConfDto conf) {
        AtomicInteger idx = new AtomicInteger(1);
        BufferedReader std = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        try {
            String lineSuccess = null;
            String lineError = null;
            // 读取正确的输出
            do {
                while ((lineSuccess = std.readLine()) != null && !lineSuccess.trim().isEmpty()) {
                    writeLog(jobName, lineSuccess, idx.getAndAdd(1), conf);
                }
                //  读取错误的输出
                while ((lineError = error.readLine()) != null && !lineError.trim().isEmpty()) {
                    writeLog(jobName, lineError, idx.getAndAdd(1), conf);
                }
            } while (process.isAlive());
        } catch (IOException e) {
            logger.error("读取任务输出信息失败，退出读取过程. 任务名是：{}, 错误信息是：{}", jobName, e.getMessage());
        }finally {
            try {
                std.close();
                error.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // TODO 使用Future模式，可以根据捕获到的控制台输出内容，可以改变任务执行是否成功的结论
        return new AsyncResult("OK");
    }

    public void writeLog(String jobId, String message, int idx, BatchRunConfDto conf) {
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
}
