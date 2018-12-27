package com.asofdate.batch.core;

import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskDefineEntity;
import com.asofdate.batch.service.ArgumentService;
import com.asofdate.batch.service.ExecService;
import com.asofdate.batch.service.JobKeyStatusService;
import com.asofdate.batch.service.TaskDefineService;
import com.asofdate.utils.JoinCode;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Scope("prototype")
public class QuartzSchedulerConfig {
    private final Logger logger = LoggerFactory.getLogger(QuartzSchedulerConfig.class);

    @Autowired
    private TaskDefineService taskDefineService;
    @Autowired
    private ExecService execService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BatchRunConfDto conf;
    private JobKeyStatusService jobKeyStatusService;
    private ArgumentService argumentService;
    private Map<String, TaskDefineEntity> taskDefineMap;
    private Map<String, GroupTaskEntity> jobKeyMap;

    /**
     * 创建调度服务
     * 每一个批次启动后，创建一个调度服务
     */
    public SchedulerFactoryBean createSchedulerFactoryBean(BatchRunConfDto conf, ResourceManagement drm) throws Exception {
        this.conf = conf;
        this.jobKeyStatusService = drm.getJobKeyStatusService();
        this.argumentService = drm.getArgumentService();

        getAllTaskForMap();
        initGroupTaskMap(drm.getJobKeyList());

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 配置DataSource后,将会出现异常
        // JobLauncher实例化对象无法序列化
        schedulerFactoryBean.setSchedulerName(conf.getBatchId());
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactoryBean.setAutoStartup(false);

        // 获取指定批次的所有任务Job
        SimpleTrigger[] simpleTriggers = new SimpleTrigger[jobKeyStatusService.getAllJob().size()];
        int i = 0;
        for (String jobId : jobKeyStatusService.getAllJob().keySet()) {
            SimpleTrigger simpleTrigger = createSimpleTrigger(jobId);
            simpleTriggers[i++] = simpleTrigger;
        }

        schedulerFactoryBean.setTriggers(simpleTriggers);
        schedulerFactoryBean.afterPropertiesSet();
        schedulerFactoryBean.getScheduler().pauseAll();
        schedulerFactoryBean.start();
        return schedulerFactoryBean;
    }

    public String getDomainId() {
        return conf.getDomainId();
    }

    public String getBatchId() {
        return conf.getBatchId();
    }

    private void initGroupTaskMap(List<GroupTaskEntity> jobKeyList) {
        jobKeyMap = new HashMap<>();
        for (GroupTaskEntity m : jobKeyList) {
            logger.debug("group task is,{}", m.toString());
            jobKeyMap.put(m.getJobKey(), m);
        }
    }

    private void getAllTaskForMap() {
        taskDefineMap = new HashMap<>();
        List<TaskDefineEntity> list = taskDefineService.findAll(conf.getDomainId(), conf.getBatchId());
        for (TaskDefineEntity m : list) {
            taskDefineMap.put(m.getTaskId(), m);
        }
    }

    private void setJobData(JobDetailFactoryBean jobDetailFactoryBean, String jobName) {
        String taskId = jobKeyMap.get(JoinCode.getTaskCode(jobName)).getTaskId();
        TaskDefineEntity tm = taskDefineMap.get(taskId);
        Map<String, Object> map = new HashMap<>();
        map.put("jobName", jobName);
        map.put("scriptPath", tm.getScriptFile());
        map.put("jobKeyStatusService", jobKeyStatusService);
        map.put("argumentService", argumentService);
        map.put("execService", execService);
        map.put("conf",conf);
        map.put("jdbcTemplate",jdbcTemplate);
        switch (tm.getTaskType()) {
            case "1":
                jobDetailFactoryBean.setJobClass(ExecuteJob.class);
                break;
            case "2":
                jobDetailFactoryBean.setJobClass(ProcedureJob.class);
                break;
            case "3":
                jobDetailFactoryBean.setJobClass(ExecuteJob.class);
                break;
            case "4":
                jobDetailFactoryBean.setJobClass(ExecuteJob.class);
            case "5":
                jobDetailFactoryBean.setJobClass(ExecuteJob.class);
                break;
            default:
                jobDetailFactoryBean.setJobClass(ExecuteJob.class);
        }
        jobDetailFactoryBean.setJobDataAsMap(map);
    }

    // 注册任务
    private JobDetailFactoryBean jobDetailFactoryBean(String jobName) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        setJobData(jobDetailFactoryBean, jobName);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(jobName);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean;
    }

    /**
     * 将任务与触发器关联
     * 每一个任务，对应着一个触发器
     * 每一个触发器，也只对应一个任务
     */
    private SimpleTrigger createSimpleTrigger(String jobName) {
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        JobDetail job = jobDetailFactoryBean(jobName).getObject();
        simpleTriggerFactoryBean.setJobDetail(job);
        simpleTriggerFactoryBean.setRepeatCount(0);
        simpleTriggerFactoryBean.setRepeatInterval(12);
        simpleTriggerFactoryBean.setGroup(jobName);
        simpleTriggerFactoryBean.setName(jobName);
        simpleTriggerFactoryBean.afterPropertiesSet();
        return simpleTriggerFactoryBean.getObject();
    }
}
