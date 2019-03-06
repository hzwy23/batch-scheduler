package com.asofdate.batch.service;

import com.asofdate.batch.dto.BatchArgumentDto;
import com.asofdate.batch.dto.BatchMonitoringDto;
import com.asofdate.batch.entity.BatchDefineEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 *
 * @author hzwy23
 */
public interface BatchDefineService {
    /**
     * 查询指定域中所有的批次信息
     *
     * @param domainId
     * @return List 返回这个域中，所有定义的批次列表信息
     */
    List<BatchDefineEntity> findAll(String domainId);

    /**
     * 查询某个域中所有运行中的批次列表信息
     *
     * @param domainId
     * @return List 返回所有运行中的批次信息
     */
    List<BatchDefineEntity> getRunning(String domainId);

    /**
     * 新增批次
     *
     * @param vo
     * @return RetMsg 当添加成功后，返回添加的行数
     */
    RetMsg addBatch(BatchDefineEntity vo);

    /**
     * 删除批次信息
     *
     * @param list 需要删除的批次列表信息
     * @return RetMsg 删除操作状态信息
     */
    RetMsg deleteBatch(List<BatchDefineEntity> list);

    /**
     * 更新批次信息
     *
     * @param vo 需要更新的批次信息
     * @return 返回更新操作的结果
     */
    RetMsg updateBatch(BatchDefineEntity vo);

    /**
     * 查询批次状态
     *
     * @param batchId
     * @return int 返回批次状态值
     */
    int getStatus(String batchId);

    /**
     * 设置批次状态值
     *
     * @param batchId
     * @param status
     * @return RetMsg 返回更新批次状态的操作结果
     */
    RetMsg setStatus(String batchId, int status);


    /**
     * 更新批次日期
     *
     * @param asofdate 批次日期
     * @param batchId  批次编码
     * @return RetMsg
     */
    RetMsg updateAsofdate(String asofdate, String batchId);

    /**
     * 根据批次号，查询批次所拥有的批次类型的参数
     *
     * @param batchId
     * @return 返回这个批次所拥有的配词类型的参数，
     * 如果参数绑定了批次日期，表示参数值与批次日期相等
     */
    List<BatchArgumentDto> findBatchArgsById(String batchId);

    /**
     * 给批次类型参数赋值
     *
     * @param list
     * @return RetMsg
     */
    RetMsg addBatchArg(List<BatchArgumentDto> list);

    /**
     * 查询批次进度
     *
     * @param batchId 批次编码
     * @return BatchMonitoringDto
     */
    BatchMonitoringDto getBatchCompletedRadio(String batchId);


    void initBatchStatus();

}
