package com.asofdate.batch;

import com.asofdate.batch.service.BatchDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Configuration
public class InitBatch {
    private static InitBatch initBatch;
    @Autowired
    private BatchDefineService batchDefineService;

    public static void initBatchInfo() {
        initBatch.batchDefineService.initBatchStatus();
    }

    @PostConstruct
    public void init() {
        this.initBatch = this;
        this.initBatch.batchDefineService = batchDefineService;
    }
}
