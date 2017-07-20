package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.ShareDomainDao;
import com.asofdate.hauth.entity.ShareDomainEntity;
import com.asofdate.hauth.service.ShareDomainService;
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
 * Created by hzwy23 on 2017/6/19.
 */
@Service
public class ShareDomainServiceImpl implements ShareDomainService {
    private final Logger logger = LoggerFactory.getLogger(ShareDomainServiceImpl.class);
    @Autowired
    private ShareDomainDao shareDomainDao;

    @Override
    public List<ShareDomainEntity> findAll(String domainId) {
        return shareDomainDao.findAll(domainId);
    }

    @Override
    public List<ShareDomainEntity> unShareTarget(String domainId) {
        return shareDomainDao.unShareTarget(domainId);
    }

    @Override
    public RetMsg add(ShareDomainEntity shareDomainEntity) {
        try {
            int size = shareDomainDao.add(shareDomainEntity);
            if (size == 1) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增域共享信息失败，请联系管理员", null);
        } catch (DataAccessException e) {
            logger.info("异常消息是；{},新增参数信息是；{}", e.getMessage(), shareDomainEntity.toString());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "新增信息失败，参数不符合要求，请联系管理员", null);
        }
    }

    @Override
    public RetMsg delete(List<ShareDomainEntity> list) {
        try {
            int size = shareDomainDao.delete(list);
            if (size == 1) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除域共享信息失败，请联系管理员", null);
        } catch (DataAccessException e) {
            logger.info("异常消息是；{}", e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "删除信息失败，参数不符合要求，请联系管理员", null);
        }
    }

    @Override
    public RetMsg update(ShareDomainEntity shareDomainEntity) {
        try {
            int size = shareDomainDao.update(shareDomainEntity);
            if (size == 1) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新域共享信息失败，请联系管理员", null);
        } catch (DataAccessException e) {
            logger.info("异常消息是；{}", e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "更新信息失败，参数不符合要求，请联系管理员", null);
        }
    }
}
