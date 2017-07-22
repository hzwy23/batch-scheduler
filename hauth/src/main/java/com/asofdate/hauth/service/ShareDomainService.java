package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.ShareDomainEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/19.
 */
public interface ShareDomainService {
    List<ShareDomainEntity> findAll(String domainId);

    List<ShareDomainEntity> unShareTarget(String domainId);

    RetMsg add(ShareDomainEntity shareDomainEntity);

    RetMsg delete(List<ShareDomainEntity> list);

    RetMsg update(ShareDomainEntity shareDomainEntity);

    int getAuthLevel(String domainId, String userDomainId);
}
