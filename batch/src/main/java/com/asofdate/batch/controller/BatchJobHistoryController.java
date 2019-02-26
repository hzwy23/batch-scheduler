package com.asofdate.batch.controller;

import com.asofdate.batch.service.BatchJobHistoryService;
import com.asofdate.batch.service.ExecService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/v1/dispatch/batch/job/history")
@Api(description ="批次调度-批次Job运行历史")
public class BatchJobHistoryController {
    private final Logger logger = LoggerFactory.getLogger(BatchJobHistoryController.class);
    @Autowired
    private BatchJobHistoryService batchJobHistoryService;
    @Autowired
    private ExecService execService;

    @RequestMapping(method = RequestMethod.GET)
    public List getJob(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        String gid = request.getParameter("gid");
        return batchJobHistoryService.findAll(uuid, gid);
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public List getLogs(HttpServletRequest request) {
        String id = request.getParameter("id");
        String job = request.getParameter("job");
        logger.debug("sid is:{},job id is:{}", id, job);
        return execService.query(id, job);
    }
}
