package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.ResourceEntity;
import com.asofdate.hauth.entity.RoleResourceEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/20.
 */
public interface RoleResourceService {
    List<RoleResourceEntity> findAll(String roleId);

    List<ResourceEntity> getOther(String roleId);

    RetMsg auth(String roleId, String resId);

    RetMsg revoke(String roleId, String resId);
}
