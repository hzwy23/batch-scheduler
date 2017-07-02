package com.asofdate.batch.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface TaskArgumentDao {
    List findAll(String domainId);

    JSONArray getTaskArg(String taskId);

    int updateSort(String sortId, String uuid);

    int deleteArg(String uuid);

    JSONObject getArgType(String argId);

    int addArg(JSONObject jsonObject);

    int updateArgValue(String argValue, String uuid);
}
