package com.asofdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"com.asofdate"})
@SpringBootApplication
@EnableAsync
public class BatchSchedulerApplication {
    // main函数，Spring Boot程序入口
    public static void main(String[] args) {
        SpringApplication.run(BatchSchedulerApplication.class, args);
    }
}