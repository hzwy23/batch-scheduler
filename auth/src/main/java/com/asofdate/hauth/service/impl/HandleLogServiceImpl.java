package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.HandleLogDao;
import com.asofdate.hauth.entity.HandleLogEntity;
import com.asofdate.hauth.service.HandleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Service
public class HandleLogServiceImpl implements HandleLogService {
    @Autowired
    private HandleLogDao handleLogDao;

    @Override
    public List<HandleLogEntity> findAll() {
        return handleLogDao.findAll();
    }

    @Override
    public List<HandleLogEntity> findAll(Integer offset, Integer limit) {
        return handleLogDao.findAll(offset, limit);
    }

    @Override
    public Integer getTotal() {
        return handleLogDao.getTotal();
    }
}
