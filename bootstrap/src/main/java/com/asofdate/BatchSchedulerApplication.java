package com.asofdate;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ThreadPoolExecutor;

@ComponentScan(basePackages = {"com.asofdate"})
@SpringBootApplication
@EnableAsync
public class BatchSchedulerApplication {
    // main函数，Spring Boot程序入口
    public static void main(String[] args) {
        SpringApplication.run(BatchSchedulerApplication.class, args);
    }
}