package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchGroupStatusDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.service.SuiteKeyStatusService;
import com.asofdate.batch.utils.GroupStatus;
import com.asofdate.batch.utils.JobStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/5/28.
 */
@Service
@Scope("prototype")
public class SuiteKeyStatusServiceImpl implements SuiteKeyStatusService {
    @Autowired
    private BatchGroupStatusDao batchGroupStatusDao;

    private BatchRunConfDto conf;

    /**
     * 批次中任务组的状态管理
     * key : 表示批次中的任务组
     * value: 表示任务组的状态信息
     * 0: 初始化
     * 1: 运行中
     * 2: 已完成
     * 3: 错误
     * 4: 停止
     */
    private Map<String, Integer> suiteKeyStatus;

    // 初始化两个变量
    public void afterPropertiesSet(BatchRunConfDto conf, List<BatchGroupEntity> list) {
        this.conf = conf;
        suiteKeyStatus = new HashMap<String, Integer>();

        /**
         * 初始化全部任务组
         * */
        for (BatchGroupEntity m : list) {
            suiteKeyStatus.put(m.getSuiteKey(), GroupStatus.SUITE_KEY_STATUS_INIT);
        }

        // 初始化任务组,状态信息表
        batchGroupStatusDao.init(conf, suiteKeyStatus);

    }

    /*
    * 设置任务组运行中
    * @param String gid 表示任务组id
    * */
    public void setSuiteRunning(String suiteKey) {
        // 修改任务组在内存中的状态
        suiteKeyStatus.put(suiteKey, GroupStatus.SUITE_KEY_STATUS_RUNNING);
        // 修改任务组在数据库中的状态
        batchGroupStatusDao.setGroupRunning(conf, suiteKey, GroupStatus.SUITE_KEY_STATUS_RUNNING);
    }

    /*
    * 设置任务组已经运行完成
    * @param String gid 表示任务组id
    * */
    public void setSuiteCompleted(String suiteKey) {
        suiteKeyStatus.put(suiteKey, GroupStatus.SUITE_KEY_STATUS_COMPLETED);
        batchGroupStatusDao.setGroupEnd(conf, suiteKey, GroupStatus.SUITE_KEY_STATUS_COMPLETED);
    }


    @Override
    public void setSuiteError(String suiteKey) {
        suiteKeyStatus.put(suiteKey, GroupStatus.SUITE_KEY_STATUS_ERROR);
        batchGroupStatusDao.setGroupEnd(conf, suiteKey, GroupStatus.SUITE_KEY_STATUS_ERROR);
    }

    /**
     * 获取任务组状态
     *
     * @param suiteKey 表示任务组gid
     */
    @Override
    public int getSuiteStatus(String suiteKey) {
        if (suiteKeyStatus.containsKey(suiteKey)) {
            return suiteKeyStatus.get(suiteKey).intValue();
        }
        return GroupStatus.SUITE_KEY_STATUS_ERROR;
    }

    // 如果所有的任务组都执行完成，表示批次执行完成
    // 否则表示批次没有主席宁完成
    @Override
    public boolean isCompleted() {
        for (Integer status : suiteKeyStatus.values()) {
            if (status != JobStatus.Job_STATUS_COMPLETED) {
                return false;
            }
        }
        return true;
    }
}
