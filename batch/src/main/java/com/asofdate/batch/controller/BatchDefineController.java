package com.asofdate.batch.controller;

import com.asofdate.batch.dto.BatchArgumentDto;
import com.asofdate.batch.entity.BatchDefineEntity;
import com.asofdate.batch.service.BatchDefineService;
import com.asofdate.batch.utils.BatchStatus;
import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.*;
import com.asofdate.utils.factory.RetMsgFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@RestController
@RequestMapping(value = "/v1/dispatch/batch/define")
@Api(description = "批次调度-批次定义管理")
public class BatchDefineController {
    private static Logger logger = LoggerFactory.getLogger(BatchDefineController.class);

    @Autowired
    private BatchDefineService batchDefineService;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询指定域下边所有的批次", notes = "如果用户请求的域编码为空，则返回用户所在域下的所有批次信息")
    @ApiImplicitParam(required = true, name = "domain_id", value = "域编码")
    public List<BatchDefineEntity> getAll(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null || domainId.isEmpty()) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }
        return batchDefineService.findAll(domainId);
    }

    /**
     * 新增批次
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增批次信息")
    public String add(HttpServletResponse response, HttpServletRequest request) {
        BatchDefineEntity bdf = parse(request);

        RetMsg retMsg = valid(bdf);
        if (!retMsg.checkCode()) {
            logger.info("ret msg is:{}", retMsg.toString());
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }

        retMsg = batchDefineService.addBatch(bdf);
        if (!retMsg.checkCode()) {
            response.setStatus(421);
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }

    /**
     * 删除批次
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除批次信息")
    public String delete(HttpServletResponse response, HttpServletRequest request) {

        String json = request.getParameter("JSON");
        List<BatchDefineEntity> args = new GsonBuilder().create().fromJson(json, new TypeToken<List<BatchDefineEntity>>() {
        }.getType());
        for (BatchDefineEntity m : args) {
            if (BatchStatus.BATCH_STATUS_RUNNING == batchDefineService.getStatus(m.getBatchId())) {
                response.setStatus(421);
                return Hret.error(421, "批次正在运行中,无法被删除", null);
            }
        }

        RetMsg msg = batchDefineService.deleteBatch(args);
        if (msg.checkCode()) {
            return Hret.success(msg);
        }

        response.setStatus(421);
        return Hret.error(msg);
    }

    /**
     * 更新批次
     *
     * @param request
     * @return 返回更新操作状态信息
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新批次信息")
    public String update(HttpServletResponse response, HttpServletRequest request) {
        BatchDefineEntity m = parse(request);
        if (batchDefineService.getStatus(m.getBatchId()) == BatchStatus.BATCH_STATUS_RUNNING) {
            response.setStatus(421);
            return Hret.error(421, "批次正在运行中,无法编辑", null);
        }

        // 参数校验
        RetMsg retMsg = valid(m);
        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }

        retMsg = batchDefineService.updateBatch(m);

        if (!retMsg.checkCode()) {
            logger.info(retMsg.toString());
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "停止批次")
    public String stop(HttpServletResponse response, HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        RetMsg retMsg = batchDefineService.setStatus(batchId, 4);
        if (SysStatus.SUCCESS_CODE != retMsg.getCode()) {
            response.setStatus(422);
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }


    @RequestMapping(value = "/argument", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询批次参数")
    public List<BatchArgumentDto> getBatchArg(HttpServletRequest request) {
        String id = request.getParameter("batch_id");
        return batchDefineService.findBatchArgsById(id);
    }

    @RequestMapping(value = "/argument", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "设置/更新批次参数", notes = "当批次参数值存在时，更新批次参数，当批次参数值不存在时，新增批次参数值")
    public String addBatchArg(HttpServletResponse response, HttpServletRequest request) {
        String json = request.getParameter("JSON");
        List<BatchArgumentDto> list = new GsonBuilder().create().fromJson(json,
                new TypeToken<List<BatchArgumentDto>>() {
                }.getType());

        RetMsg retMsg = batchDefineService.addBatchArg(list);
        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }

    @RequestMapping(value = "/asofdate", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新批次日期")
    public String updateAsofdate(HttpServletResponse response, HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String asofdate = request.getParameter("as_of_date");

        // 校验批次日期
        if (!Validator.isTime(asofdate)) {
            response.setStatus(421);
            return Hret.error(421, "批次日期格式不正确，格式必须是：2008-08-08 20:08:08", null);
        }

        RetMsg retMsg = batchDefineService.updateAsofdate(asofdate, batchId);
        if (SysStatus.SUCCESS_CODE != retMsg.getCode()) {
            response.setStatus(421);
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }

    private RetMsg valid(BatchDefineEntity bdf) {
        if (bdf == null) {
            return RetMsgFactory.getRetMsg(422, "请求参数解析失败，请联系管理员", null);
        }
        // 批次编码校验
        if (!Validator.isWord(bdf.getCodeNumber())) {
            return RetMsgFactory.getRetMsg(422, "批次编码必须由1-30位字母，数字组成", null);
        }
        // 校验批次名称
        if (bdf.getBatchDesc() == null || bdf.getBatchDesc().isEmpty() || bdf.getBatchDesc().length() > 200) {
            return RetMsgFactory.getRetMsg(423, "批次名称不能为空，且长度小于200", null);
        }
        // 校验批次时间
        if (!Validator.isTime(bdf.getAsOfDate())) {
            logger.info("批次日期是：{}", bdf.getAsOfDate());
            return RetMsgFactory.getRetMsg(423, "批次日期格式不正确，格式是：2008-08-08 20:08:08", null);
        }
        // 校验终止时间
        if (!Validator.isTime(bdf.getCompleteDate())) {
            return RetMsgFactory.getRetMsg(423, "完成日期格式不正确，格式是：2008-08-08 20:08:08", null);
        }

        // 校验批次执行频率
        if (!Validator.isNumeric(bdf.getPaggingFreq())) {
            return RetMsgFactory.getRetMsg(422, "执行频率不能为空，且必须是正整数", null);
        }

        // 执行频率单位校验
        if (bdf.getPaggingFreqMult() == null || bdf.getPaggingFreqMult().isEmpty()) {
            return RetMsgFactory.getRetMsg(422, "请选择执行频率单位", null);
        }

        return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "OK", null);
    }

    private BatchDefineEntity parse(HttpServletRequest request) {
        BatchDefineEntity batchDefineEntity = new BatchDefineEntity();
        String batchId = JoinCode.join(request.getParameter("domain_id"), request.getParameter("batch_id"));
        batchDefineEntity.setBatchId(batchId);
        batchDefineEntity.setCodeNumber(request.getParameter("batch_id"));
        batchDefineEntity.setRetMsg("");
        batchDefineEntity.setCompleteDate(request.getParameter("complete_date"));
        batchDefineEntity.setDomainId(request.getParameter("domain_id"));
        batchDefineEntity.setBatchDesc(request.getParameter("batch_desc"));
        batchDefineEntity.setBatchStatus(request.getParameter("batch_status"));
        batchDefineEntity.setAsOfDate(request.getParameter("as_of_date"));
        batchDefineEntity.setPaggingFreq(request.getParameter("pagging_freq"));
        batchDefineEntity.setPaggingFreqMult(request.getParameter("pagging_freq_mult"));
        return batchDefineEntity;
    }
}
