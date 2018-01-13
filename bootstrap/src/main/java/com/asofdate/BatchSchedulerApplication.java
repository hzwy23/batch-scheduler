package com.asofdate;

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

    }
}