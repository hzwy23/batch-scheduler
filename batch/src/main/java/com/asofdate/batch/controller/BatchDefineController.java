package com.asofdate.batch.controller;

import com.asofdate.batch.dto.BatchArgumentDTO;
import com.asofdate.batch.entity.BatchDefineEntity;
import com.asofdate.batch.service.BatchDefineService;
import com.asofdate.batch.utils.BatchStatus;
import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.JoinCode;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
public class BatchDefineController {
    private static Logger logger = LoggerFactory.getLogger(BatchDefineController.class);

    @Autowired
    private BatchDefineService batchDefineService;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<BatchDefineEntity> getAll(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }
        return batchDefineService.findAll(domainId);
    }

    /**
     * 新增批次
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(HttpServletResponse response, HttpServletRequest request) {
        RetMsg retMsg = batchDefineService.addBatch(parse(request));
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
    public String update(HttpServletResponse response, HttpServletRequest request) {
        BatchDefineEntity m = parse(request);
        if (batchDefineService.getStatus(m.getBatchId()) == BatchStatus.BATCH_STATUS_RUNNING) {
            response.setStatus(421);
            return Hret.error(421, "批次正在运行中,无法编辑", null);
        }
        RetMsg retMsg = batchDefineService.updateBatch(m);

        if (!retMsg.checkCode()) {
            logger.info(retMsg.toString());
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ResponseBody
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
    public List<BatchArgumentDTO> getBatchArg(HttpServletRequest request) {
        String id = request.getParameter("batch_id");
        return batchDefineService.findBatchArgsById(id);
    }

    @RequestMapping(value = "/argument", method = RequestMethod.POST)
    @ResponseBody
    public String addBatchArg(HttpServletResponse response, HttpServletRequest request) {
        String json = request.getParameter("JSON");
        List<BatchArgumentDTO> list = new GsonBuilder().create().fromJson(json,
                new TypeToken<List<BatchArgumentDTO>>() {
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
    public String updateAsofdate(HttpServletResponse response, HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String asofdate = request.getParameter("as_of_date");
        RetMsg retMsg = batchDefineService.updateAsofdate(asofdate, batchId);
        if (SysStatus.SUCCESS_CODE != retMsg.getCode()) {
            response.setStatus(421);
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
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
        batchDefineEntity.setPaggingFreq(Integer.parseInt(request.getParameter("pagging_freq")));
        batchDefineEntity.setPaggingFreqMult(request.getParameter("pagging_freq_mult"));
        return batchDefineEntity;
    }
}
