package com.asofdate.batch.dao.impl;

import com.asofdate.batch.dao.BatchDefineDao;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchDefineEntity;
import com.asofdate.batch.sql.SqlDefine;
import com.asofdate.utils.TimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class BatchDefineDaoImpl implements BatchDefineDao {
    private final Logger logger = LoggerFactory.getLogger(BatchDefineDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List findAll(String domainId) {
        RowMapper<BatchDefineEntity> rowMapper = new BeanPropertyRowMapper<BatchDefineEntity>(BatchDefineEntity.class);
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_107, rowMapper, domainId);
        return list;
    }

    @Override
    public List<BatchDefineEntity> getRunning(String domainId) {
        RowMapper<BatchDefineEntity> rowMapper = new BeanPropertyRowMapper<BatchDefineEntity>(BatchDefineEntity.class);
        List list = jdbcTemplate.query(SqlDefine.sys_rdbms_165, rowMapper, domainId);
        return list;
    }

    @Override
    public int add(BatchDefineEntity m) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_128,
                m.getBatchId(),
                m.getCodeNumber(),
                m.getBatchDesc(),
                m.getBatchStatus(),
                m.getAsOfDate(),
                m.getRetMsg(),
                m.getCompleteDate(),
                m.getDomainId(),
                m.getPaggingFreq(),
                m.getPaggingFreqMult());
    }

    @Transactional
    @Override
    public String delete(List<BatchDefineEntity> m) {
        try {
            for (BatchDefineEntity l : m) {
                jdbcTemplate.update(SqlDefine.sys_rdbms_129, l.getBatchId(), l.getDomainId());
            }
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public int update(BatchDefineEntity m) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_130,
                m.getBatchDesc(),
                m.getAsOfDate(),
                m.getCompleteDate(),
                m.getPaggingFreq(),
                m.getPaggingFreqMult(),
                m.getBatchId(),
                m.getDomainId());
    }

    @Override
    public int getStatus(String batchId) {
        return jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_131, Integer.class, batchId);
    }

    @Override
    public int batchPagging(String batchId, String asOfDate) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_176, asOfDate, batchId);
    }

    @Override
    public int setStatus(String batchId, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_140, Integer.toString(status), batchId);
    }

    @Override
    public int updateAsofdate(String asofdate, String batchId) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_161, asofdate, batchId);
    }

    @Override
    public int runBatchInit(String batchId) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_178, batchId);
    }

    @Override
    public int destoryBatch(String batchId, String retMsg, int status) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_179, retMsg, status, batchId);
    }

    @Transactional
    @Override
    public int saveHistory(String batchId) {
        String asOfDate = getBatchAsOfDate(batchId);

        String uuid = UUID.randomUUID().toString();
        jdbcTemplate.update(SqlDefine.sys_rdbms_192, uuid, batchId);
        jdbcTemplate.update(SqlDefine.sys_rdbms_195, uuid, batchId, asOfDate);
        jdbcTemplate.update(SqlDefine.sys_rdbms_212, uuid, batchId, asOfDate);
        return jdbcTemplate.update(SqlDefine.sys_rdbms_196, uuid, batchId, asOfDate);
    }

    @Override
    public String getBatchAsOfDate(String batchId) {
        try {
            String asOfDate = jdbcTemplate.queryForObject(SqlDefine.sys_rdbms_102, String.class, batchId);
            return TimeFormat.formatTime(asOfDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BatchDefineEntity findDetailsByBatchId(String batchId) {
        BatchDefineEntity ret = new BatchDefineEntity();
        jdbcTemplate.query(SqlDefine.sys_rdbms_210, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                String batchStatus = resultSet.getString("batch_status");
                String asOfDate = resultSet.getString("as_of_date");
                String completeDate = resultSet.getString("complete_date");
                String paggingFreq = resultSet.getString("pagging_freq");
                String paggingFreqMult = resultSet.getString("pagging_freq_mult");

                ret.setAsOfDate(TimeFormat.formatTime(asOfDate));
                ret.setBatchStatus(batchStatus);
                ret.setCompleteDate(TimeFormat.formatTime(completeDate));
                ret.setPaggingFreq(paggingFreq);
                ret.setPaggingFreqMult(paggingFreqMult);
            }
        }, new Object[]{batchId});

        return ret;
    }
}
