package com.asofdate.batch.service.impl;

import com.asofdate.batch.dao.BatchArgumentDao;
import com.asofdate.batch.dao.BatchDefineDao;
import com.asofdate.batch.dao.BatchJobStatusDao;
import com.asofdate.batch.dto.BatchArgumentDto;
import com.asofdate.batch.dto.BatchMonitoringDto;
import com.asofdate.batch.dto.BatchRunConfDto;
import com.asofdate.batch.entity.BatchArgumentEntiry;
import com.asofdate.batch.entity.BatchDefineEntity;
import com.asofdate.batch.service.BatchDefineService;
import com.asofdate.batch.service.SysConfigService;
import com.asofdate.batch.utils.BatchStatus;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Service
public class BatchDefineServiceImpl implements BatchDefineService {
    private final Logger logger = LoggerFactory.getLogger(BatchDefineServiceImpl.class);
    @Autowired
    private BatchDefineDao batchDefineDao;

    @Autowired
    private BatchArgumentDao batchArgumentDao;

    @Autowired
    private BatchJobStatusDao batchJobStatusDao;

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public List<BatchDefineEntity> findAll(String domainId) {
        return batchDefineDao.findAll(domainId);
    }

    @Override
    public List<BatchDefineEntity> getRunning(String domainId) {
        return batchDefineDao.getRunning(domainId);
    }

    @Override
    public RetMsg addBatch(BatchDefineEntity vo) {
        try {
            int size = batchDefineDao.add(vo);
            if (1 != size) {
                return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增批次失败，请联系管理员", size);
            }
            return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), vo);
        }
    }

    @Override
    public RetMsg deleteBatch(List<BatchDefineEntity> vo) {
        try {
            String msg = batchDefineDao.delete(vo);
            if ("success".equals(msg)) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除批次信息失败", vo);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), vo);
        }
    }

    @Override
    public RetMsg updateBatch(BatchDefineEntity m) {
        try {
            int size = batchDefineDao.update(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新批次信息失败，请联系管理员", m);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), m);
        }
    }

    @Override
    public int getStatus(String batchId) {
        return batchDefineDao.getStatus(batchId);
    }

    @Override
    public RetMsg setStatus(String batchId, int status) {
        try {
            int size = batchDefineDao.setStatus(batchId, status);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "设置批次状态信息失败，请联系管理员", batchId);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), batchId);
        }
    }

    @Override
    public RetMsg runBatchInit(String batchId) {
        try {
            int size = batchDefineDao.runBatchInit(batchId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次初始化失败，批次无法启动", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg batchPagging(BatchRunConfDto conf) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        Date completeDate = null;
        try {
            date = sdf.parse(conf.getAsOfDate());
            completeDate = sdf.parse(conf.getCompleteDate());

        } catch (ParseException e) {
            e.printStackTrace();
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次日志格式不正确，退出翻页执行操作。", conf);
        }
        Integer freq = Integer.parseInt(conf.getPaggingFreq());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateAddVal;
        switch (conf.getPaggingFreqMult()) {
            case "MI":
                calendar.add(Calendar.MINUTE, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "H":
                calendar.add(Calendar.HOUR, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "D":
                calendar.add(Calendar.DATE, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "W":
                calendar.add(Calendar.DATE, 7 * freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "M":
                calendar.add(Calendar.MONTH, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "Q":
                calendar.add(Calendar.MONDAY, 3 * freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "Y":
                calendar.add(Calendar.YEAR, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            default:
                return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "执行频率单位不正确，退出批次调度", conf);
        }

        if (completeDate.before(calendar.getTime())) {
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次运行到终止日期，批次调度正常退出服务", conf);
        }

        // 获取批次现在的状态信息
        int status = batchDefineDao.getStatus(conf.getBatchId());
        if (BatchStatus.BATCH_STATUS_COMPLETED != status) {
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次状态不是完成状态，无法继续翻页执行，退出服务", conf);
        }

        try {
            int size = batchDefineDao.batchPagging(conf.getBatchId(), dateAddVal);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次翻页执行失败，请联系管理员", null);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), conf.getBatchId());
        }
    }

    @Override
    public RetMsg updateAsofdate(String asofdate, String batchId) {
        try {
            int size = batchDefineDao.updateAsofdate(asofdate, batchId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新批次日期失败，请联系管理员", asofdate);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), asofdate);
        }
    }

    @Override
    public List<BatchArgumentDto> findBatchArgsById(String batchId) {
        List<BatchArgumentEntiry> batchArgList = batchArgumentDao.findBatchArgsById(batchId);
        String asOfDate = batchArgumentDao.getAsOfDate(batchId);
        String flag;

        List<BatchArgumentDto> list = new ArrayList<>();

        for (BatchArgumentEntiry batchArg : batchArgList) {
            BatchArgumentDto ret = new BatchArgumentDto();
            flag = batchArg.getBindAsOfDate();
            if ("1".equals(flag)) {
                batchArg.setArgValue(asOfDate);
            }
            ret.setCodeNumber(batchArg.getCodeNumber());
            ret.setDomainId(batchArg.getDomainId());
            ret.setArgId(batchArg.getArgId());
            ret.setBatchId(batchId);
            ret.setArgValue(batchArg.getArgValue());
            ret.setArgDesc(batchArg.getArgDesc());
            ret.setBindAsOfDate(flag);
            list.add(ret);
        }
        return list;
    }

    @Override
    public RetMsg addBatchArg(List<BatchArgumentDto> list) {
        List<BatchArgumentEntiry> args = new ArrayList<>();
        for (BatchArgumentDto m : list) {
            BatchArgumentEntiry entity = new BatchArgumentEntiry();
            entity.setDomainId(m.getDomainId());
            entity.setBatchId(m.getBatchId());
            entity.setArgId(m.getArgId());
            entity.setArgValue(m.getArgValue());
            args.add(entity);
        }

        try {
            int size = batchArgumentDao.add(args);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "给批次添加参数失败，请联系管理员", list);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), list);
        }
    }

    @Override
    public BatchMonitoringDto getBatchCompletedRadio(String batchId) {
        String asOfDate = batchDefineDao.getBatchAsOfDate(batchId);
        BatchRunConfDto conf = new BatchRunConfDto();
        conf.setAsOfDate(asOfDate);
        conf.setBatchId(batchId);

        int completedCnt = batchJobStatusDao.getCompletedCnt(conf);
        int totalCnt = batchJobStatusDao.getTotalCnt(conf);

        BatchMonitoringDto batchMonitoringDto = new BatchMonitoringDto();
        batchMonitoringDto.setAsOfDate(asOfDate);

        if (totalCnt == 0) {
            batchMonitoringDto.setRatio((float) 0);
            return batchMonitoringDto;
        }
        float ratio = (float) completedCnt / (float) totalCnt;
        batchMonitoringDto.setRatio(ratio);
        return batchMonitoringDto;

    }

    @Override
    public RetMsg destoryBatch(String batchId, String retMsg, int status) {
        try {
            int size = batchDefineDao.destoryBatch(batchId, retMsg, status);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "销毁批次失败，请联系管理员", batchId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), batchId);
        }
    }

    @Override
    public void saveHistory(String batchId) {
        try {
            batchDefineDao.saveHistory(batchId);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public String getBatchAsOfDate(String batchId) {
        return batchDefineDao.getBatchAsOfDate(batchId);
    }

    @Override
    public BatchRunConfDto initConf(String batchId, String domainId) {
        BatchDefineEntity bde = batchDefineDao.findDetailsByBatchId(batchId);

        String basePath = sysConfigService.getValue(domainId, "CONF0001");
        String redisSwitch = sysConfigService.getValue(domainId, "CONF0002");
        BatchRunConfDto batchRunConfDto = new BatchRunConfDto();
        batchRunConfDto.setBatchId(batchId);
        batchRunConfDto.setDomainId(domainId);
        batchRunConfDto.setAsOfDate(bde.getAsOfDate());
        batchRunConfDto.setCompleteDate(bde.getCompleteDate());
        batchRunConfDto.setPaggingFreq(bde.getPaggingFreq());
        batchRunConfDto.setPaggingFreqMult(bde.getPaggingFreqMult());
        batchRunConfDto.setBasePath(basePath);
        batchRunConfDto.setRedisSwitch(redisSwitch);
        logger.debug(batchRunConfDto.toString());
        return batchRunConfDto;
    }

    @Override
    public void initBatchStatus() {
        batchDefineDao.initBatchStatus();
    }
}
