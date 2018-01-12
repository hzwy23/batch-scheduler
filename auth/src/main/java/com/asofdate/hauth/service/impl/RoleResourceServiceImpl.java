package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.RoleResourceDao;
import com.asofdate.hauth.entity.ResourceEntity;
import com.asofdate.hauth.entity.RoleResourceEntity;
import com.asofdate.hauth.service.RoleResourceService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/20.
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<RoleResourceEntity> findAll(String roleId) {
        return roleResourceDao.findAll(roleId);
    }

    @Override
    public List<ResourceEntity> getOther(String roleId) {
        return roleResourceDao.getOther(roleId);
    }

    @Override
    public RetMsg auth(String roleId, String resId) {
        try {
            int size = roleResourceDao.auth(roleId, resId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "授权失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg revoke(String roleId, String resId) {
        try {
            int size = roleResourceDao.revoke(roleId, resId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "移除权限失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }
}
