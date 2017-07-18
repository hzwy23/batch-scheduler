package com.asofdate.batch.controller;

import com.asofdate.batch.entity.BatchGroupStatusEntity;
import com.asofdate.batch.service.BatchGroupRunningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/v1/dispatch/batch/group/running")
public class BatchGroupRunningController {
    private final Logger logger = LoggerFactory.getLogger(BatchGroupRunningController.class);
    @Autowired
    private BatchGroupRunningService batchGroupRunningService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BatchGroupStatusEntity> findAll(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String asOfDate = request.getParameter("as_of_date");
        logger.info("batch id is:{}, as of date is:{}", batchId, asOfDate);
        return batchGroupRunningService.findAll(batchId, asOfDate);
    }

    @RequestMapping(value = "/ratio", method = RequestMethod.GET)
    public Integer getRatio(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String gid = request.getParameter("gid");
        String asOfDate = request.getParameter("as_of_date");
        return batchGroupRunningService.getRatio(batchId, gid,asOfDate);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public BatchGroupStatusEntity getDetails(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String suiteKey = request.getParameter("suiteKey");
        String asOfDate = request.getParameter("as_of_date");

        logger.info("batch id is:{},suiteKey is:{},as of date is:{}", batchId, suiteKey, asOfDate);
        return batchGroupRunningService.getDetails(batchId, suiteKey, asOfDate);
    }

}
