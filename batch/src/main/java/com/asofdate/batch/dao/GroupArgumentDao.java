package com.asofdate.batch.dao;

import com.asofdate.batch.entity.GroupArgumentEntity;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/30.
 */
public interface GroupArgumentDao {
    List<GroupArgumentEntity> findAll(String domainId);

    JSONArray getGroupArg(String id);

    int updateArg(String argValue, String uuid, String argId);
}
