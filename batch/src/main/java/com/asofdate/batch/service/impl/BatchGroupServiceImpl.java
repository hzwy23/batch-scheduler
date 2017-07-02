package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchGroupDao;
import com.asofdate.batch.dto.BatchGroupDTO;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/25.
 */
@Service
public class BatchGroupServiceImpl implements BatchGroupService {
    private final Logger logger = LoggerFactory.getLogger(BatchGroupServiceImpl.class);
    @Autowired
    private BatchGroupDao batchGroupDao;

    @Override
    public List<BatchGroupEntity> findByBatchId(String domainId, String batchId) {
        List<BatchGroupEntity> list = batchGroupDao.findAll(domainId);
        for (int i = 0; i < list.size(); i++) {
            if (!batchId.equals(list.get(i).getBatchId())) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Override
    public List<BatchGroupEntity> getGroup(String batchId) {
        return batchGroupDao.getGroup(batchId);
    }

    @Override
    public RetMsg addGroup(List<BatchGroupDTO> list) {
        List<BatchGroupEntity> args = new ArrayList<>();

        for (BatchGroupDTO m : list) {
            String domainId = m.getDomainId();
            String batchId = m.getBatchId();
            String groupId = m.getGroupId();
            BatchGroupEntity entity = new BatchGroupEntity();
            entity.setDomainId(domainId);
            entity.setBatchId(batchId);
            entity.setGroupId(groupId);
            logger.debug(entity.toString());
            args.add(entity);
        }

        try {
            int size = batchGroupDao.addGroup(args);
            if (1 == size) {
                logger.debug("add group success.");
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            logger.info("add Group failure.");
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "添加任务组失败，请联系管理员", null);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), list);
        }
    }

    @Override
    public RetMsg deleteGroup(List<BatchGroupDTO> list) {
        List<BatchGroupEntity> args = new ArrayList<>();
        for (BatchGroupDTO m : list) {
            String id = m.getId();
            BatchGroupEntity entity = new BatchGroupEntity();
            entity.setId(id);
            args.add(entity);
        }
        try {
            int size = batchGroupDao.deleteGroup(args);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), list);
        }
    }

    @Override
    public List<BatchGroupEntity> getDependency(String batchid, String id) {
        return batchGroupDao.getDependency(batchid, id);
    }
}
