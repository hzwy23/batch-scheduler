package com.asofdate.batch.core;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobFactory;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class RegisterJob implements JobFactory {
    private final Job job;

    public RegisterJob(Job job) {
        this.job = job;
    }

    public final Job createJob() {
        return this.job;
    }

    public String getJobName() {
        return this.job.getName();
    }
}
