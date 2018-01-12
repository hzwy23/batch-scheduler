package com.asofdate.hauth.dao;

import com.asofdate.hauth.entity.DomainEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public interface DomainDao {

    List<DomainEntity> findAll();

    List<DomainEntity> getAll();

    /**
     * 更新系统中的域信息
     */
    int update(DomainEntity domainEntity);

    /**
     * 删除系统中的域信息
     */
    int delete(List<DomainEntity> list);

    /**
     * 新增域信息
     */
    int add(DomainEntity domainEntity);

    /**
     * 查询指定域的详细信息
     *
     * @param domainId
     */
    DomainEntity getDomainDetails(String domainId);

}
