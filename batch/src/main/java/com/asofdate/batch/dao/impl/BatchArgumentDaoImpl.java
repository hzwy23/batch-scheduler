package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchArgumentDao;
import com.asofdate.batch.entity.BatchArgumentEntiry;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
@Repository
public class BatchArgumentDaoImpl implements BatchArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId, String batchId) {
        RowMapper<BatchArgumentEntiry> rowMapper = new BeanPropertyRowMapper<BatchArgumentEntiry>(BatchArgumentEntiry.class);

        // 获取固定参数,任务参数,任务组参数
        List<BatchArgumentEntiry> list = jdbcTemplate.query(SqlDefine.sys_rdbms_163, rowMapper, domainId, batchId);

        //获取批次类型参数
        List<BatchArgumentEntiry> list2 = jdbcTemplate.query(SqlDefine.sys_rdbms_164, rowMapper, domainId, batchId);

        return bindAsofdate(list, list2);
    }

    @Override
    public List<BatchArgumentEntiry> findBatchArgsById(String batchId) {
        RowMapper<BatchArgumentEntiry> rowMapper = new BeanPropertyRowMapper<>(BatchArgumentEntiry.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_139, rowMapper, batchId);
    }

    @Override
    public String getAsOfDate(String batchId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_157, String.class, batchId);
    }


    @Transactional
    @Override
    public int add(List<BatchArgumentEntiry> list) {
        for (BatchArgumentEntiry m : list) {

            if (isExists(m.getBatchId(), m.getArgId())) {
                if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_160,
                        m.getArgValue(),
                        m.getBatchId(),
                        m.getArgId())) {
                    return -1;
                }
            } else {
                if (1 != jdbcTemplate.update(SqlDefine.sys_rdbms_158,
                        m.getBatchId(),
                        m.getArgId(),
                        m.getArgValue(),
                        m.getDomainId())) {
                    return -1;
                }
            }
        }
        return 1;
    }

    private List<BatchArgumentEntiry> bindAsofdate(List<BatchArgumentEntiry> ret, List<BatchArgumentEntiry> source) {
        for (BatchArgumentEntiry m : source) {
            // 绑定批次日期
            if ("1".equals(m.getBindAsOfDate())) {
                String asOfDate = jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_157,
                        String.class,
                        m.getBatchId());
                m.setArgValue(asOfDate);
            }
            ret.add(m);
        }
        return ret;
    }

    private boolean isExists(String batchID, String argId) {
        int flag = jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_159, Integer.class, batchID, argId);
        if (flag >= 1) {
            return true;
        }
        return false;
    }
}
