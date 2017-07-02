package com.asofdate.batch.service;

import com.asofdate.batch.entity.SysConfigEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/15.
 *
 * @author hzwy23
 */
public interface SysConfigService {
    /**
     * 查询ETL系统参数情况
     *
     * @param domainId 域编码
     * @return List ETL系统级参数配置信息
     */
    List<SysConfigEntity> findAll(String domainId);

    /**
     * 修改参数值
     *
     * @param domainId
     * @param configId
     * @param configValue
     */
    int setValue(String domainId, String configId, String configValue);

    /**
     * 查询参数值
     *
     * @param domainId
     * @param configId
     */
    String getValue(String domainId, String configId);
}
