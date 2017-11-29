package com.asofdate.hauth.dao;

import com.asofdate.hauth.entity.DomainShareEntity;
import com.asofdate.hauth.entity.ShareDomainEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/19.
 */
public interface ShareDomainDao {
    List<ShareDomainEntity> findAll(String domainId);

    List<ShareDomainEntity> unShareTarget(String domainId);

    int add(ShareDomainEntity shareDomainEntity);

    int delete(List<ShareDomainEntity> list);

    int update(ShareDomainEntity shareDomainEntity);

    int getAuthLevel(String domainId, String userDomainId);

    /**
    * 查询某个指定的域,授权给了哪些域
    * */
    List<DomainShareEntity> findShareTo(String domainId);

    Set<String> findShareDomain(String targetDomainId);
}
