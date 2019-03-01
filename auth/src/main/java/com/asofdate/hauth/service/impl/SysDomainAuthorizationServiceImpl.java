package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.jpa.SysDomainAuthorizationDao;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.entity.SysDomainAuthorization;
import com.asofdate.hauth.service.DomainService;
import com.asofdate.hauth.service.SysDomainAuthorizationService;
import com.asofdate.hauth.vo.SysDomainAuthorizationAddParamVo;
import com.asofdate.hauth.vo.SysDomainAuthorizationVo;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.TimeFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class SysDomainAuthorizationServiceImpl implements SysDomainAuthorizationService {

    @Autowired
    private SysDomainAuthorizationDao sysDomainAuthorizationDao;

    @Autowired
    private DomainService domainService;

    @Override
    public List<SysDomainAuthorizationVo> findAll(String userId) {
        List<SysDomainAuthorization> ret = sysDomainAuthorizationDao.findByUserId(userId);
        List<SysDomainAuthorizationVo> result = new ArrayList<>();
        for (SysDomainAuthorization item : ret) {
            SysDomainAuthorizationVo element = new SysDomainAuthorizationVo();
            BeanUtils.copyProperties(item, element);
            result.add(element);
        }
        return result;
    }

    public List<DomainEntity> findUnauth(String userId) {
        // 查询用户已经获取到的项目
        List<SysDomainAuthorization> owners = sysDomainAuthorizationDao.findByUserId(userId);
        Set<String> domainIdSet = new HashSet<>();
        if (owners != null && !owners.isEmpty()) {
            for (SysDomainAuthorization item : owners) {
                domainIdSet.add(item.getDomainId());
            }
        }
        List<DomainEntity> domainList = domainService.getAll();
        List<DomainEntity> result = new ArrayList<>();
        for (DomainEntity item : domainList) {
            if (domainIdSet.contains(item.getDomain_id())) {
                continue;
            }
            result.add(item);
        }
        return result;
    }

    @Override
    @Transactional
    public RetMsg modifyDefaultDomain(String uuid, String userId) {
        sysDomainAuthorizationDao.updateClearDefaultDomain(userId);
        int size = sysDomainAuthorizationDao.updateUserDefaultDomain(uuid);
        if (size == 1) {
            return new RetMsg(200, "success", null);
        }
        return new RetMsg(10010, "项目不存在", uuid);
    }

    @Override
    public RetMsg grant(SysDomainAuthorizationAddParamVo paramVo, String handleUserId) {
        int size = sysDomainAuthorizationDao.countByDomainIdAndUserId(paramVo.getDomainId(), paramVo.getUserId());
        if (size >= 1) {
            log.info("用户已经被授予这个项目的访问权限，{}", paramVo);
            return new RetMsg(10011, "项目已经授权给用户，不能重复授权", paramVo);
        }
        String current = TimeFormat.currentTime();
        SysDomainAuthorization element = new SysDomainAuthorization();
        element.setUuid(UUID.randomUUID().toString().replace("-", ""));
        element.setAuthorizationLevel(paramVo.getAuthorizationLevel());
        element.setDefaultDomain(false);
        element.setUserId(paramVo.getUserId());
        element.setDomainId(paramVo.getDomainId());
        element.setCreateDate(current);
        element.setModifyDate(current);
        element.setModifyUser(handleUserId);
        element.setCreateUser(handleUserId);
        element = sysDomainAuthorizationDao.save(element);
        return new RetMsg(200, "Success", element);
    }

    @Override
    public RetMsg revoke(String uuid) {
        sysDomainAuthorizationDao.deleteById(uuid);
        return new RetMsg(200, "success", uuid);
    }

    @Override
    public RetMsg modify(String uuid, int level) {
        int size = sysDomainAuthorizationDao.updateLevel(level, uuid);
        if (size == 1) {
            return new RetMsg(200, "success", uuid);
        }
        return new RetMsg(10012, "项目ID不存在，不能进行修改", uuid);
    }

}
