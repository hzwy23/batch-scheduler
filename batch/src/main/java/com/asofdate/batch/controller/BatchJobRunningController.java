package com.asofdate.batch.controller;

import com.asofdate.batch.entity.BatchJobStatusEntity;
import com.asofdate.batch.service.BatchJobRunningService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/v1/dispatch/batch/job/running")
@Api(description = "批次调度-批次Job运行监控")
public class BatchJobRunningController {
    @Autowired
    private BatchJobRunningService batchJobRunningService;

    @RequestMapping(method = RequestMethod.GET)
    public List getJob(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String suiteKey = request.getParameter("suiteKey");
        String asOfDate = request.getParameter("as_of_date");

        return batchJobRunningService.findAll(batchId, suiteKey, asOfDate);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public BatchJobStatusEntity getDetails(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String suiteKey = request.getParameter("suiteKey");
        String jobKey = request.getParameter("jobKey");
        return batchJobRunningService.getDetails(batchId, suiteKey, jobKey);
    }
}
