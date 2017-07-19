package com.asofdate.batch.controller;

import com.asofdate.batch.service.BatchGroupHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/v1/dispatch/batch/group/history")
@Api("批次调度-批次任务组运行历史")
public class BatchGroupHistoryController {
    @Autowired
    private BatchGroupHistoryService batchGroupHistoryService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询批次任务组运行历史信息")
    public List findAll(HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        return batchGroupHistoryService.findAll(uuid);
    }
}
