package com.asofdate.batch.controller;

import com.asofdate.batch.entity.BatchGroupStatusEntity;
import com.asofdate.batch.service.BatchGroupRunningService;
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
    @Autowired
    private BatchGroupRunningService batchGroupRunningService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BatchGroupStatusEntity> findAll(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        return batchGroupRunningService.findAll(batchId);
    }

    @RequestMapping(value = "/ratio", method = RequestMethod.GET)
    public Integer getRatio(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String gid = request.getParameter("gid");
        return batchGroupRunningService.getRatio(batchId, gid);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public BatchGroupStatusEntity getDetails(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String gid = request.getParameter("gid");
        return batchGroupRunningService.getDetails(batchId, gid);
    }

}
