package com.asofdate.hauth.dao;

import com.asofdate.hauth.entity.DomainEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public interface DomainDao {
    List<DomainEntity> findAll();

    List<DomainEntity> getAll();

    int update(DomainEntity domainEntity);

    int delete(List<DomainEntity> list);

    int add(DomainEntity domainEntity);

    DomainEntity getDomainDetails(String domainId);
}
