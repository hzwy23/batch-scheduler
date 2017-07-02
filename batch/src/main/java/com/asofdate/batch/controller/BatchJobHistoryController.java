package com.asofdate.batch.controller;

import com.asofdate.batch.service.BatchJobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@RestController
@RequestMapping(value = "/v1/dispatch/batch/job/history")
public class BatchJobHistoryController {
    @Autowired
    private BatchJobHistoryService batchJobHistoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List getJob(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String gid = request.getParameter("gid");
        return batchJobHistoryService.findAll(uuid, gid);
    }
}
