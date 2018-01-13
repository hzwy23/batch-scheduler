package com.asofdate.batch.config;

import com.asofdate.batch.service.BatchDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Configuration
public class InitBatchStatus {

    @Autowired
    private BatchDefineService batchDefineService;

    @PostConstruct
    public void init() {
        batchDefineService.initBatchStatus();
    }
}
