package com.asofdate;

import com.asofdate.batch.InitBatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.asofdate"})
@SpringBootApplication
public class BatchSchedulerApplication {
    // main函数，Spring Boot程序入口
    public static void main(String[] args) {

        // 启动服务
        SpringApplication.run(BatchSchedulerApplication.class, args);

        // 初始化批次调度系统中批次状态
        InitBatch.initBatchInfo();
    }
}