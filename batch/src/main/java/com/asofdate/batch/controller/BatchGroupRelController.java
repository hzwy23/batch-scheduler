package com.asofdate.batch.controller;

import com.asofdate.batch.dto.BatchGroupDto;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/27.
 */
@RestController
@Api(description ="批次调度-批次内任务组配置管理")
public class BatchGroupRelController {
    private final Logger logger = LoggerFactory.getLogger(BatchGroupRelController.class);
    @Autowired
    private BatchGroupService batchGroupService;

    @RequestMapping(value = "/v1/dispatch/batch/define/group", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询批次内任务组")
    public List<BatchGroupEntity> getGroup(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        logger.debug("batch id is:{}", batchId);
        return batchGroupService.getGroup(batchId);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "在批次中添加任务组")
    public String addGroup(HttpServletResponse response, HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String domainId = request.getParameter("domain_id");
        String json = request.getParameter("JSON");
        List<BatchGroupDto> list = new GsonBuilder().create().fromJson(json, new TypeToken<List<BatchGroupDto>>() {
        }.getType());

        for (BatchGroupDto m : list) {
            m.setDomainId(domainId);
            m.setBatchId(batchId);
        }

        RetMsg retMsg = batchGroupService.addGroup(list);
        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }


    @RequestMapping(value = "/v1/dispatch/batch/define/group/list/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除批次中配置的任务组信息")
    public String deleteGroupList(HttpServletResponse response, HttpServletRequest request) {
        String json = request.getParameter("JSON");
        List<BatchGroupDto> list = new GsonBuilder().create().fromJson(json, new TypeToken<List<BatchGroupDto>>() {
        }.getType());
        RetMsg retMsg = batchGroupService.deleteGroup(list);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }


    @RequestMapping(value = "/v1/dispatch/batch/define/group/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGroup(HttpServletResponse response, HttpServletRequest request) {
        String id = request.getParameter("id");
        logger.debug("suiteKey is:{}", id);

        List<BatchGroupDto> list = new ArrayList<>();
        BatchGroupDto dto = new BatchGroupDto();
        dto.setSuiteKey(id);
        list.add(dto);

        RetMsg retMsg = batchGroupService.deleteGroup(list);

        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }
}
