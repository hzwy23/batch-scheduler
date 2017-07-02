package com.asofdate.batch.controller;

import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.batch.service.GroupDependencyService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/27.
 */
@RestController
public class GroupDependencyController {
    @Autowired
    private GroupDependencyService groupDependencyService;
    @Autowired
    private BatchGroupService batchGroupService;

    /**
     * 查询某一个指定的任务组可以选择的上级依赖任务组列表信息
     *
     * @param request id 表示批次中任务组的id号
     */
    @RequestMapping(value = "/v1/dispatch/batch/define/group/dependency", method = RequestMethod.GET)
    @ResponseBody
    public List<BatchGroupEntity> getDependency(HttpServletRequest request) {
        String id = request.getParameter("id");
        return groupDependencyService.getUp(id);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group/dependency/add", method = RequestMethod.GET)
    @ResponseBody
    public List<BatchGroupEntity> canAddDependency(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String id = request.getParameter("id");
        return batchGroupService.getDependency(batchId, id);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group/dependency", method = RequestMethod.POST)
    @ResponseBody
    public String addDependency(HttpServletResponse response, HttpServletRequest request) {

        String JSON = request.getParameter("JSON");
        JSONArray jsonArray = new JSONArray(JSON);
        RetMsg retMsg = groupDependencyService.addGroupDependency(jsonArray);
        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.success(retMsg);
        }
        return Hret.success(retMsg);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group/dependency/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGroupDependency(HttpServletResponse response, HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        RetMsg retMsg = groupDependencyService.deleteGroupDependency(uuid);
        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }
}
