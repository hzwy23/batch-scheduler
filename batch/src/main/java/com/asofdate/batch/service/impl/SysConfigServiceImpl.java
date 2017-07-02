package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.SysConfigDao;
import com.asofdate.batch.entity.SysConfigEntity;
import com.asofdate.batch.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/15.
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public List<SysConfigEntity> findAll(String domainId) {
        return sysConfigDao.findAll(domainId);
    }

    @Override
    public int setValue(String domainId, String configId, String configValue) {
        return sysConfigDao.setValue(domainId, configId, configValue);
    }

    @Override
    public String getValue(String domainId, String configId) {
        return sysConfigDao.getValue(domainId, configId);
    }
}
