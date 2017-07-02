package com.asofdate.hauth.dao;

import com.asofdate.hauth.entity.OrgEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface OrgDao {
    List<OrgEntity> findAll(String domainId);

    List<OrgEntity> findSub(String domainId, String orgId);

    int add(OrgEntity orgEntity);

    int delete(List<OrgEntity> list);

    int update(OrgEntity orgEntity);
}
