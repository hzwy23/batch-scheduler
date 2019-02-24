package com.asofdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class BatchSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchSchedulerApplication.class, args);
    }
}