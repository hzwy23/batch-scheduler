package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchGroupDao;
import com.asofdate.batch.dto.BatchGroupDTO;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
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
        return batchGroupDao.findAll(domainId, batchId);
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
            String id = m.getSuiteKey();
            BatchGroupEntity entity = new BatchGroupEntity();
            entity.setSuiteKey(id);
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
    public List<BatchGroupEntity> getAvaiableDependencySuite(String batchid, String id) {
        return batchGroupDao.getDependency(batchid, id);
    }


    @Override
    public List<BatchGroupEntity> getDependencySuite(String suiteKey) {
        return batchGroupDao.getGroupDependency(suiteKey);
    }


    @Override
    public RetMsg deleteGroupDependency(String uuid) {
        try {
            int size = batchGroupDao.deleteGroupDependency(uuid);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组依赖关系失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), uuid);
        }
    }

    @Override
    public RetMsg addGroupDependency(List<GroupDependencyEntity> list) {
        try {
            int size = batchGroupDao.addGroupDependency(list);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "给任务组添加依赖失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
