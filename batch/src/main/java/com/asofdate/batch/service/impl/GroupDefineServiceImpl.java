package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.GroupArgumentDao;
import com.asofdate.batch.dao.GroupDefineDao;
import com.asofdate.batch.entity.GroupDefineEntity;
import com.asofdate.batch.service.GroupDefineService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Service
public class GroupDefineServiceImpl implements GroupDefineService {
    @Autowired
    private GroupDefineDao groupDefineDao;
    @Autowired
    private GroupArgumentDao groupArgumentDao;

    @Override
    public List<GroupDefineEntity> findAll(String domainId) {
        return groupDefineDao.findAll(domainId);
    }

    @Override
    public RetMsg update(GroupDefineEntity m) {
        try {
            int size = groupDefineDao.update(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新任务组信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), m);
        }
    }

    @Override
    public RetMsg delete(List<GroupDefineEntity> m) {
        try {
            String msg = groupDefineDao.delete(m);
            if ("success".equals(msg)) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), m);
        }
    }

    @Override
    public RetMsg add(GroupDefineEntity m) {
        try {
            int size = groupDefineDao.add(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务组信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg updateArg(String argValue, String uuid, String argId) {
        try {
            int size = groupArgumentDao.updateArg(argValue, uuid, argId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新任务组类型参数失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
