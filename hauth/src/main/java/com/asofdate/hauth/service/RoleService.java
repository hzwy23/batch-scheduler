package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.RoleEntity;
import com.asofdate.utils.RetMsg;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface RoleService {
    List<RoleEntity> findAll(String domainId);

    List<RoleEntity> getOther(String userId);

    List<RoleEntity> getOwner(String userId);

    RoleEntity getDetails(String roleId);

    int auth(JSONArray jsonArray, String modifyUserId);

    int revoke(JSONArray jsonArray);

    int batchAuth(JSONArray jsonArray, String modifyUserId);

    RetMsg add(RoleEntity roleEntity);

    RetMsg delete(List<RoleEntity> list);

    RetMsg update(RoleEntity roleEntity);
}
