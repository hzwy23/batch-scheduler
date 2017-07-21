package com.asofdate.batch.controller;

import com.asofdate.utils.JoinCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Controller
@Api("批次调度-静态页面管理")
public class StaticPageController {
    private final Logger logger = LoggerFactory.getLogger(StaticPageController.class);

    @RequestMapping(value = "/v1/dispatch/monitoring/page", method = RequestMethod.GET)
    public String getBatchMonitoringPage() {
        return "dispatch/batch_monitoring";
    }

    @RequestMapping(value = "/v1/dispatch/system/config/page", method = RequestMethod.GET)
    public String getBatchConfigPage() {
        return "dispatch/batch_config";
    }

    @RequestMapping(value = "/v1/dispatch/argument/page", method = RequestMethod.GET)
    public String getPage() {
        return "dispatch/argument_define";
    }

    @RequestMapping(value = "/v1/dispatch/batch/page", method = RequestMethod.GET)
    public String getBatchDefinePage() {
        return "dispatch/batch_define";
    }

    @RequestMapping(value = "/v1/dispatch/group/page", method = RequestMethod.GET)
    public String getGroupDefinePage() {
        return "dispatch/group_define";
    }

    @RequestMapping(value = "/v1/dispatch/task/page", method = RequestMethod.GET)
    public String getTaskDefinePage() {
        return "dispatch/task_define";
    }

    @ApiOperation(value = "任务组中任务配置管理")
    @RequestMapping(value = "/v1/dispatch/groupAndTask/page", method = RequestMethod.GET)
    public String getGroupTaskPage(HttpServletRequest request, Map<String, Object> map) {
        String groupId = request.getParameter("groupId");
        String groupDesc = request.getParameter("groupDesc");
        map.put("groupId", groupId);
        map.put("groupDesc", groupDesc);
        logger.debug("groupid is:{},groupdesc is:{}", groupId, groupDesc);
        return "dispatch/group_task";
    }

    @RequestMapping(value = "/v1/dispatch/batchAndGroup/page", method = RequestMethod.GET)
    @ApiOperation(value = "批次域任务组配置管理页面")
    public String getBatchGroupPage(HttpServletRequest request, Map<String, Object> map) {
        String batchId = request.getParameter("batchId");
        String batchDesc = request.getParameter("batchDesc");
        String domainId = JoinCode.getFirst(batchId);
        logger.debug("domain id is:{},batch id is:{},batch desc is:{}", domainId, batchId, batchDesc);
        map.put("batchId", batchId);
        map.put("batchDesc", batchDesc);
        map.put("domainId", domainId);
        return "dispatch/batch_group";
    }

    @RequestMapping(value = "/v1/dispatch/batch/history", method = RequestMethod.GET)
    public String getBatchHistory(HttpServletRequest request) {
        return "dispatch/batch_history";
    }

    @RequestMapping(value = "/v1/dispatch/batch/group/monitoring/page", method = RequestMethod.GET)
    public String getGroupMonitoring(HttpServletRequest request, Map<String, Object> map) {
        map.put("batchDesc", request.getParameter("batch_desc"));
        map.put("uuid", request.getParameter("uuid"));
        return "dispatch/batch_group_status_monitoring";
    }

    @RequestMapping(value = "/v1/dispatch/batch/job/monitoring/page", method = RequestMethod.GET)
    public String getJobMonitoring(HttpServletRequest request, Map<String, Object> map) {
        map.put("groupDesc", request.getParameter("group_desc"));
        map.put("batchDesc", request.getParameter("batch_desc"));
        map.put("uuid", request.getParameter("uuid"));
        map.put("gid", request.getParameter("gid"));
        return "dispatch/batch_job_status_monitoring";
    }


    @RequestMapping(value = "/v1/dispatch/batch/group/running/monitoring/page", method = RequestMethod.GET)
    public String getGroupRunningMonitoring(HttpServletRequest request, Map<String, Object> map) {
        String batchDesc = request.getParameter("batch_desc");
        String batchId = request.getParameter("batch_id");
        String asOfDate = request.getParameter("as_of_date");
        logger.debug("batch id is:{}, batch Desc is:{}, asOfDate is:{}", batchId, batchDesc, asOfDate);
        map.put("batchDesc", batchDesc);
        map.put("batchId", batchId);
        map.put("asOfDate", asOfDate);
        return "dispatch/batch_group_status_running_monitoring";
    }

    @RequestMapping(value = "/v1/dispatch/batch/job/running/monitoring/page", method = RequestMethod.GET)
    public String getJobRunningMonitoring(HttpServletRequest request, Map<String, Object> map) {
        String groupDesc = request.getParameter("group_desc");
        String batchDesc = request.getParameter("batch_desc");
        String batchId = request.getParameter("batch_id");
        String suiteKey = request.getParameter("suiteKey");
        String asOfDate = request.getParameter("as_of_date");
        logger.debug("batch id is:{},batch desc is:{},group desc is:{},as_of_date is:{}, suiteKey is:{}", batchId, batchDesc, groupDesc, asOfDate, suiteKey);
        map.put("groupDesc", groupDesc);
        map.put("batchDesc", batchDesc);
        map.put("batchId", batchId);
        map.put("suiteKey", suiteKey);
        map.put("asOfDate", asOfDate);
        return "dispatch/batch_job_status_running_monitoring";
    }
}
