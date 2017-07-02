package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.DomainDao;
import com.asofdate.hauth.dao.DomainShareDao;
import com.asofdate.hauth.dto.DomainDTO;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.service.DomainService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Service
public class DomainServiecImpl implements DomainService {

    @Autowired
    private DomainDao domainDao;
    @Autowired
    private DomainShareDao domainShareDao;

    @Override
    public DomainDTO findAll(String domainId) {
        List<DomainEntity> list = domainDao.findAll();
        Set<String> set = domainShareDao.findAll(domainId);
        for (int i = 0; i < list.size(); i++) {
            if (!set.contains(list.get(i).getDomain_id())) {
                list.remove(i);
                i--;
            }
        }
        DomainDTO domainDTO = new DomainDTO();
        domainDTO.setDomainId(domainId);
        domainDTO.setOwnerList(list);
        return domainDTO;
    }

    @Override
    public List<DomainEntity> getAll() {
        return domainDao.getAll();
    }

    @Override
    public RetMsg update(DomainEntity domainEntity) {
        try {
            int size = domainDao.update(domainEntity);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新域信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg delete(List<DomainEntity> list) {
        try {
            int size = domainDao.delete(list);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除域信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg add(DomainEntity domainEntity) {
        try {
            int size = domainDao.add(domainEntity);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增域信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public DomainEntity getDomainDetails(String domainId) {
        return domainDao.getDomainDetails(domainId);
    }
}