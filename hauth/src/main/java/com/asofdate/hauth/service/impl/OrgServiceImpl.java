package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.OrgDao;
import com.asofdate.hauth.entity.OrgEntity;
import com.asofdate.hauth.service.OrgService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgDao orgDao;

    @Override
    public List<OrgEntity> findAll(String domainId) {
        return orgDao.findAll(domainId);
    }

    @Override
    public List<OrgEntity> findSub(String domainId, String orgId) {
        return orgDao.findSub(domainId, orgId);
    }

    @Override
    public RetMsg add(OrgEntity orgEntity) {
        try {
            int size = orgDao.add(orgEntity);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "添加机构信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg delete(List<OrgEntity> list) {
        try {
            int size = orgDao.delete(list);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除机构信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg update(OrgEntity orgEntity) {
        try {
            int size = orgDao.update(orgEntity);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新机构信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
