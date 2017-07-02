package com.asofdate.hauth.dao;

import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.entity.DomainShareEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public interface DomainShareDao {
    /*
    * 查询某个指定的域,能够访问到的所有域
    * */
    List<DomainShareEntity> findAuth(String targetDomainId);

    /*
    * 查询某个指定的域,没有授予权限的域列表
    * */
    List<DomainEntity> findUnshareTo(String domainId);

    /*
    * 查询某个指定的域,授权给了哪些域
    * */
    List<DomainShareEntity> findShareTo(String domainId);

    Set<String> findAll(String targetDomainId);

}
