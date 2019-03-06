package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.ExecDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.ExecLogEntity;
import com.asofdate.batch.service.ExecService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/13.
 */
@Service
public class ExecServiceImpl implements ExecService {
    private final Logger logger = LoggerFactory.getLogger(ExecServiceImpl.class);
    @Autowired
    private ExecDao execDao;

    @Override
    public List<ExecLogEntity> query(String id, String jobId) {
        try {
            return execDao.query(id, jobId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public RetMsg init(BatchRunConfDto confDto) {
        try {
            execDao.init(confDto);
            return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
        } catch (DataAccessException e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), confDto.getBatchId());
        }
    }
}
