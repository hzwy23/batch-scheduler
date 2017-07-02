package com.asofdate.batch.controller;

import com.asofdate.batch.dto.BatchHistoryDTO;
import com.asofdate.batch.service.BatchHistoryService;
import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
@RestController
@RequestMapping(value = "/v1/dispatch/history")
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
        String JSON = request.getParameter("JSON");
        JSONArray jsonArray = new JSONArray(JSON);
        List<BatchHistoryDTO> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject js = (JSONObject) jsonArray.get(i);
            BatchHistoryDTO dto = new BatchHistoryDTO();
            dto.setUuid(js.getString("uuid"));
            list.add(dto);
        }

        RetMsg retMsg = batchHistoryService.delete(list);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }
}
