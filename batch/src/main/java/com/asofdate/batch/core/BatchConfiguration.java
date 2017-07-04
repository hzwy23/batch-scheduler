package com.asofdate.batch.core;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by hzwy23 on 2017/5/20.
 */
@Configuration
public class BatchConfiguration {

    @Autowired
    public DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TaskletFactory taskletFactory;

    public Step stepOne(String jobName,String typeId, String scriptFile, String basePath) {
        return stepBuilderFactory.get(jobName)
                .tasklet(taskletFactory.getTasklet(typeId, scriptFile, basePath))
                .build();
    }

    /*
    * @jobName job名称
    * 创建Job名称为jobName的任务
    * */
    public Job job(String jobName, String typeId, String scriptFile, String basePath) throws Exception {
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(stepOne(jobName,typeId, scriptFile, basePath))
                .build();
    }

    /*
    * 注册任务
    * */
    public JobRegistry createJobRegistry(String jobName, String typeId, String scriptFile, String basePath) {
        MapJobRegistry jobRegistry = new MapJobRegistry();
        try {
            jobRegistry.register(new RegisterJob(job(jobName, typeId, scriptFile, basePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobRegistry;
    }


    public JobRepository createJobRepository() {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(new ResourcelessTransactionManager());
        try {
            jobRepositoryFactoryBean.afterPropertiesSet();
            return jobRepositoryFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 创建SimpleJobLauncher对象
    * */
    public JobLauncher createJobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        try {
            simpleJobLauncher.setJobRepository(jobRepository);
            simpleJobLauncher.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleJobLauncher;
    }

    public JobExplorer createJobExplorer() {
        JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
        jobExplorerFactoryBean.setDataSource(dataSource);
        try {
            jobExplorerFactoryBean.afterPropertiesSet();
            JobExplorer jobExplorer = jobExplorerFactoryBean.getObject();
            return jobExplorer;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JobOperator createJobOperator(JobLauncher jobLauncher, JobExplorer jobExplorer, JobRegistry jobRegistry, JobRepository jobRepository) {
        SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
        simpleJobOperator.setJobLauncher(jobLauncher);
        simpleJobOperator.setJobExplorer(jobExplorer);
        simpleJobOperator.setJobRegistry(jobRegistry);
        simpleJobOperator.setJobRepository(jobRepository);
        try {
            simpleJobOperator.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleJobOperator;
    }
}