package com.asofdate.batch.controller;

import com.asofdate.batch.dto.BatchHistoryDTO;
import com.asofdate.batch.service.BatchHistoryService;
import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
@RestController
@RequestMapping(value = "/v1/dispatch/history")
@Api("批次调度-批次历史管理")
public class BatchHistoryController {
    @Autowired
    private BatchHistoryService batchHistoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List findAll(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null || domainId.isEmpty()) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }
        return batchHistoryService.findAll(domainId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteHistory(HttpServletResponse response, HttpServletRequest request) {
        String json = request.getParameter("JSON");
        List<BatchHistoryDTO> list = new GsonBuilder().create().fromJson(json,
                new TypeToken<List<BatchHistoryDTO>>() {
                }.getType());

        RetMsg retMsg = batchHistoryService.delete(list);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }
}
