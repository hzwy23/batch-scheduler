package com.asofdate.hauth.service;

import com.asofdate.hauth.dto.DomainDTO;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public interface DomainService {
    DomainDTO findAll(String domainId);

    List<DomainEntity> getAll();

    RetMsg update(DomainEntity domainEntity);

    RetMsg delete(List<DomainEntity> list);

    RetMsg add(DomainEntity domainEntity);

    DomainEntity getDomainDetails(String domainId);
}
