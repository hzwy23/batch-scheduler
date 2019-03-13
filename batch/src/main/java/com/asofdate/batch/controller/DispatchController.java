package com.asofdate.batch.controller;

import com.asofdate.batch.service.feign.TaskExecutorFeign;
import com.asofdate.batch.utils.ResultBody;
import com.asofdate.utils.Hret;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hzwy23 on 2017/5/23.
 */
@Controller
@Scope("prototype")
@Api(description = "批次调度-批次启动管理")
@Slf4j
public class DispatchController {


    @Autowired
    private TaskExecutorFeign taskExecutorFeign;

    @RequestMapping(value = "/v1/dispatch/start", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "启动批次", notes = "启动批次时，需要两个参数，一个是域编码，另一个是批次编码")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "domain_id", value = "域编码"),
            @ApiImplicitParam(required = true, name = "batch_id", value = "批次编码"),
    })
    public String start(HttpServletResponse response, HttpServletRequest request) {

        String domainId = request.getParameter("domain_id");
        String batchId = request.getParameter("batch_id");

        if (domainId == null || batchId == null) {
            response.setStatus(421);
            log.info("batch id or domain jobKey is null ,batch jobKey is: {}, domain jobKey is: {}", batchId, domainId);
            return Hret.error(421, "domain_id is empty or batch_id is empty", null);
        }
        try {
            ResultBody retMsg =  taskExecutorFeign.start(batchId,domainId);
            if (retMsg == null){
                log.info("调用执行节点，返回值为空");
                return Hret.success();
            }
            return Hret.success(retMsg.getCode(), retMsg.getMessage(), retMsg.getData());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("调用执行节点失败，失败原因是：{}", e.getMessage());
            return Hret.success();
        }
    }
}
