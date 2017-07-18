package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by hzwy23 on 2017/5/20.
 */
@Configuration
@Scope("prototype")
public class TaskletConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TaskletFactory taskletFactory;


    /**
     * @jobName job名称
     * 创建Job名称为jobName的任务
     */
    public Job job(BatchRunConfDto conf, String jobName, String typeId, String scriptFile) {
        Step step = stepOne(conf, jobName, typeId, scriptFile);
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    /**
     * 创建一个Step
     * 将每一个任务注册到一个Step中
     */
    private Step stepOne(BatchRunConfDto conf, String jobName, String typeId, String scriptFile) {
        Tasklet tasklet = taskletFactory.getTasklet(conf, typeId, scriptFile);
        return stepBuilderFactory.get(jobName).tasklet(tasklet).build();
    }
}