package com.asofdate.hauth.service.impl;

import com.asofdate.hauth.dao.jpa.SysDomainAuthorizationDao;
import com.asofdate.hauth.entity.SysDomainAuthorization;
import com.asofdate.hauth.service.SysDomainAuthorizationService;
import com.asofdate.hauth.vo.SysDomainAuthorizationVo;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.TimeFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SysDomainAuthorizationServiceImpl implements SysDomainAuthorizationService {

    @Autowired
    private SysDomainAuthorizationDao sysDomainAuthorizationDao;

    @Override
    public List<SysDomainAuthorizationVo> findAll(String userId) {
        List<SysDomainAuthorization> ret = sysDomainAuthorizationDao.findByUserId(userId);
        List<SysDomainAuthorizationVo> result = new ArrayList<>();
        for(SysDomainAuthorization item: ret){
            SysDomainAuthorizationVo element = new SysDomainAuthorizationVo();
            BeanUtils.copyProperties(item, element);
            result.add(element);
        }
        return result;
    }

    @Override
    @Transactional
    public RetMsg modifyDefaultDomain(String uuid, String userId) {
        sysDomainAuthorizationDao.updateClearDefaultDomain(userId);
        int size = sysDomainAuthorizationDao.updateUserDefaultDomain(uuid);
        if (size == 1){
            return new RetMsg(200,"success",null);
        }
        return new RetMsg(10010,"项目不存在",uuid);
    }

    @Override
    public RetMsg grant(String domainId, String userId, int level, String handleUserId) {
        int size = sysDomainAuthorizationDao.countByDomainIdAndUserId(domainId, userId);
        if (size == 1) {
            return new RetMsg(10011,"项目已经授权给用户，不能重复授权",domainId+","+userId);
        }
        String current = TimeFormat.currentTime();
        SysDomainAuthorization element = new SysDomainAuthorization();
        element.setUuid(UUID.randomUUID().toString().replace("-",""));
        element.setAuthorizationLevel(level);
        element.setDefaultDomain(false);
        element.setUserId(userId);
        element.setDomainId(domainId);
        element.setCreateDate(current);
        element.setModifyDate(current);
        element.setModifyUser(handleUserId);
        element.setCreateUser(handleUserId);
        sysDomainAuthorizationDao.save(element);
        return new RetMsg(200,"SUccess", element);
    }

    @Override
    public RetMsg revoke(String uuid) {
        sysDomainAuthorizationDao.deleteById(uuid);
        return new RetMsg(200,"success", uuid);
    }

    @Override
    public RetMsg modify(String uuid, int level) {
        int size = sysDomainAuthorizationDao.updateLevel(uuid,level);
        if (size == 1) {
            return new RetMsg(200,"success", uuid);
        }
        return new RetMsg(10012,"修改授权模式失败",uuid);
    }

}
