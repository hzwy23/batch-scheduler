package com.asofdate.batch.controller;

import com.asofdate.utils.JoinCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Controller
public class StaticPageController {
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

    @RequestMapping(value = "/v1/dispatch/groupAndTask/page", method = RequestMethod.GET)
    public String getGroupTaskPage(HttpServletRequest request, Map<String, Object> map) {
        map.put("groupId", request.getParameter("groupId"));
        map.put("groupDesc", request.getParameter("groupDesc"));
        return "dispatch/group_task";
    }

    @RequestMapping(value = "/v1/dispatch/batchAndGroup/page", method = RequestMethod.GET)
    public String getBatchGroupPage(HttpServletRequest request, Map<String, Object> map) {
        map.put("batchId", request.getParameter("batchId"));
        map.put("batchDesc", request.getParameter("batchDesc"));
        map.put("domainId", JoinCode.getFirst(request.getParameter("batchId")));
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
        map.put("batchDesc", request.getParameter("batch_desc"));
        map.put("batchId", request.getParameter("batch_id"));
        return "dispatch/batch_group_status_running_monitoring";
    }

    @RequestMapping(value = "/v1/dispatch/batch/job/running/monitoring/page", method = RequestMethod.GET)
    public String getJobRunningMonitoring(HttpServletRequest request, Map<String, Object> map) {
        map.put("groupDesc", request.getParameter("group_desc"));
        map.put("batchDesc", request.getParameter("batch_desc"));
        map.put("batchId", request.getParameter("batch_id"));
        map.put("gid", request.getParameter("gid"));
        return "dispatch/batch_job_status_running_monitoring";
    }
}
