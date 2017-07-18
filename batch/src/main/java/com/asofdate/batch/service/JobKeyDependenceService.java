package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchRunConfDto;

import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface JobKeyDependenceService {

    Set<String> getJobDependence(String suiteKey, String jobKey);

    /**
     * 使用spring注入bean后，调用这个方法初始化对象属性
     *
     * @param conf
     */
    void afterPropertiesSet(BatchRunConfDto conf);

}
