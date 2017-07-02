package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.TaskDependencyDao;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskDependencyEntity;
import com.asofdate.sql.SqlDefine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Repository
public class TaskDependencyDaoImpl implements TaskDependencyDao {
    private final Logger logger = LoggerFactory.getLogger(TaskDependencyDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TaskDependencyEntity> findAll(String domainId) {
        RowMapper<TaskDependencyEntity> rowMapper = new BeanPropertyRowMapper<TaskDependencyEntity>(TaskDependencyEntity.class);
        List<TaskDependencyEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_113, rowMapper, domainId);
        return list;
    }

    @Override
    public List<TaskDependencyEntity> findById(String domainId, String batchId) {
        List<TaskDependencyEntity> list = findAll(domainId);
        for (int i = 0; i < list.size(); i++) {
            if (batchId.equals(list.get(i))) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Transactional
    @Override
    public List<GroupTaskEntity> getTaskDependency(String id) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<>(GroupTaskEntity.class);
        List<GroupTaskEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_134, rowMapper, id);
        return list;
    }

    @Override
    public List<GroupTaskEntity> getGroupTasks(String groupId, String id) {
        logger.info("groupId is :" + groupId);
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<>(GroupTaskEntity.class);
        List<GroupTaskEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_150, rowMapper, groupId);
        Set<String> set = getChildren(groupId, id);
        List<GroupTaskEntity> owner = getTaskDependency(id);

        // 获取已经存在的依赖任务
        for (GroupTaskEntity m : owner) {
            set.add(m.getUpId());
        }

        /*
        * 在任务依赖选择项目中,删除已经存在的依赖,并删除当前job的下级任务
        * 任务组内任务禁止产生闭环
        * */
        for (int i = 0; i < list.size(); i++) {
            String subid = list.get(i).getId();
            if (set.contains(subid)) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Override
    public int addTaskDependency(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_151,
                    jsonObject.getString("id"),
                    jsonObject.getString("up_id"),
                    jsonObject.getString("domain_id"))) {
                return -1;
            }
        }
        return 1;
    }

    private Set<String> getChildren(String groupId, String id) {
        Set<String> set = new HashSet<>();
        RowMapper<TaskDependencyEntity> rowMapper = new BeanPropertyRowMapper<>(TaskDependencyEntity.class);
        List<TaskDependencyEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_075, rowMapper, groupId);
        children(list, id, set);
        set.add(id);
        return set;
    }

    private void children(List<TaskDependencyEntity> all, String id, Set<String> set) {
        for (TaskDependencyEntity m : all) {
            String upId = m.getUpId();
            if (upId == null || set.contains(m.getId())) {
                continue;
            }
            if (upId.equals(id)) {
                set.add(m.getId());
                children(all, m.getId(), set);
            }
        }
    }

    @Override
    public int deleteTaskDependency(String uuid) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_152, uuid);
    }
}
