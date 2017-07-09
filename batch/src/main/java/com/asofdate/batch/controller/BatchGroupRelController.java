package com.asofdate.batch.controller;

import com.asofdate.batch.dto.BatchGroupDTO;
import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
public class BatchGroupRelController {
    @Autowired
    private BatchGroupService batchGroupService;

    @RequestMapping(value = "/v1/dispatch/batch/define/group", method = RequestMethod.GET)
    @ResponseBody
    public List<BatchGroupEntity> getGroup(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        return batchGroupService.getGroup(batchId);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group", method = RequestMethod.POST)
    @ResponseBody
    public String addGroup(HttpServletResponse response, HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String domainId = request.getParameter("domain_id");
        String json = request.getParameter("JSON");
        List<BatchGroupDTO> list = new GsonBuilder().create().fromJson(json, new TypeToken<List<BatchGroupDTO>>() {
        }.getType());

        for (BatchGroupDTO m : list) {
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
    public String deleteGroupList(HttpServletResponse response, HttpServletRequest request) {
        String json = request.getParameter("JSON");
        List<BatchGroupDTO> list = new GsonBuilder().create().fromJson(json, new TypeToken<List<BatchGroupDTO>>() {
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
        List<BatchGroupDTO> list = new ArrayList<>();
        BatchGroupDTO dto = new BatchGroupDTO();
        dto.setId(id);
        list.add(dto);

        RetMsg retMsg = batchGroupService.deleteGroup(list);

        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }
}
