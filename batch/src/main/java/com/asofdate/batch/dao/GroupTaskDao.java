package com.asofdate.batch.dao;

import org.json.JSONArray;

import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface GroupTaskDao {
    List findAll(String domainId);

    JSONArray getTask(String groupId);

    String getTaskId(String id);

    int deleteTask(String id);

    int deleteTask(Set<String> args);

    int addTask(String id, String groupId, String taskId, String domainId);

    int addArg(JSONArray jsonArray);
}
