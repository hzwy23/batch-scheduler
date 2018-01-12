package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.GroupTaskDao;
import com.asofdate.batch.dao.impl.sql.BatchSqlText;
import com.asofdate.batch.dto.GroupDefineDto;
import com.asofdate.batch.dto.GroupTaskDto;
import com.asofdate.batch.entity.GroupTaskEntity;
import com.asofdate.batch.entity.TaskDependencyEntity;
import com.asofdate.utils.JoinCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class GroupTaskDaoImpl implements GroupTaskDao {
    private final Logger logger = LoggerFactory.getLogger(GroupTaskDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<GroupTaskEntity>(GroupTaskEntity.class);
        List list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_109"), rowMapper, domainId);
        return list;
    }

    @Override
    public List<GroupTaskEntity> getJobList(String groupId) {
        List<GroupTaskEntity> list = new ArrayList<>();
        jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_133"), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                GroupTaskEntity one = new GroupTaskEntity();
                one.setCodeNumber(resultSet.getString("code_number"));
                one.setDomainId(resultSet.getString("domain_id"));
                one.setGroupId(resultSet.getString("group_id"));
                one.setJobKey(resultSet.getString("job_key"));
                one.setPosTop(resultSet.getInt("pos_top"));
                one.setTaskId(resultSet.getString("task_id"));
                one.setTaskType(resultSet.getString("task_type"));
                one.setTaskTypeDesc(resultSet.getString("task_type_desc"));
                one.setTaskDesc(resultSet.getString("task_type_desc"));
                one.setPosLeft(resultSet.getInt("pos_left"));
                list.add(one);
            }
        }, groupId);
        logger.debug("group id is:{},result is:{}", groupId, list);
        return list;
    }

    @Override
    public String getTaskIdByJobKey(String jobKey) {
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_136"), String.class, jobKey);
    }

    @Override
    public int deleteJob(String jobKey) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_147"), jobKey);
    }

    @Transactional
    @Override
    public int deleteJob(Set<String> args) {
        for (String id : args) {
            logger.info("delete job,  job_key is: {}", id);
            int size = jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_147"), id);
            logger.debug("retuen size is:{}", size);
            if (size != 1) {
                return -1;
            }
        }
        return 1;
    }

    @Override
    public int addJob(String id, String groupId, String taskId, String domainId) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_148"), id, groupId, taskId, domainId);
    }

    @Transactional
    @Override
    public int addJobArguments(List<GroupDefineDto> list) {
        for (GroupDefineDto m : list) {
            if (1 != jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_149"),
                    m.getJobKey(),
                    m.getArgId(),
                    m.getArgValue(),
                    m.getDomainId())) {
                return 0;
            }
        }
        return 1;
    }


    @Override
    public List<GroupTaskEntity> getJobList(String groupId, String jobKey) {
        logger.debug("groupId is :" + groupId);
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<>(GroupTaskEntity.class);
        List<GroupTaskEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_150"), rowMapper, groupId);

        Set<String> set = getChildren(groupId, jobKey);
        List<GroupTaskEntity> owner = getTaskDependency(jobKey);

        // 获取已经存在的依赖任务
        for (GroupTaskEntity m : owner) {
            set.add(m.getUpJobKey());
        }

        /*
        * 在任务依赖选择项目中,删除已经存在的依赖,并删除当前job的下级任务
        * 任务组内任务禁止产生闭环
        * */
        for (int i = 0; i < list.size(); i++) {
            String subid = list.get(i).getJobKey();
            if (set.contains(subid)) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }


    private Set<String> getChildren(String groupId, String id) {
        Set<String> set = new HashSet<>();
        RowMapper<TaskDependencyEntity> rowMapper = new BeanPropertyRowMapper<>(TaskDependencyEntity.class);
        List<TaskDependencyEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_075"), rowMapper, groupId);
        children(list, id, set);
        set.add(id);
        return set;
    }

    private void children(List<TaskDependencyEntity> all, String id, Set<String> set) {
        for (TaskDependencyEntity m : all) {
            String upId = m.getUpJobKey();
            if (upId == null || set.contains(m.getJobKey())) {
                continue;
            }
            if (upId.equals(id)) {
                set.add(m.getJobKey());
                children(all, m.getJobKey(), set);
            }
        }
    }

    @Transactional
    @Override
    public List<GroupTaskEntity> getTaskDependency(String id) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<>(GroupTaskEntity.class);
        List<GroupTaskEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_134"), rowMapper, id);
        return list;
    }

    @Override
    public int addTaskDependency(List<TaskDependencyEntity> list, String groupId) {
        // 删除任务组内所有的连线信息
        jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_218"), groupId);

        for (TaskDependencyEntity m : list) {
            try {
                if (1 != jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_151"),
                        DigestUtils.sha1Hex(JoinCode.join(m.getJobKey(), m.getUpJobKey())),
                        m.getJobKey(),
                        m.getUpJobKey(),
                        m.getDomainId())) {
                    logger.warn("新增任务失败");
                    return -1;
                }
            } catch (DuplicateKeyException e) {
                logger.warn("任务已经存在，刷新成功，{}", m.getJobKey());
            }
        }
        return 1;
    }

    @Override
    public int deleteTaskDependency(String uuid) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_152"), uuid);
    }

    @Transactional
    @Override
    public int updateTaskLocation(List<GroupTaskDto> list) {
        for (GroupTaskDto one : list) {
            if (1 != jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_217"),
                    one.getLeft(), one.getTop(), one.getId())) {
                return -1;
            }
        }
        return 0;
    }

}
