package com.asofdate.batch.controller;

import com.asofdate.batch.entity.BatchGroupEntity;
import com.asofdate.batch.entity.GroupDependencyEntity;
import com.asofdate.batch.service.BatchGroupService;
import com.asofdate.batch.service.SuiteKeyDependenceService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(description = "批次调度-批次内任务组依赖管理")
public class GroupDependencyController {
    private final Logger logger = LoggerFactory.getLogger(GroupDependencyController.class);
    @Autowired
    private SuiteKeyDependenceService suiteKeyDependenceService;
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
        String id = request.getParameter("suiteKey");
        return batchGroupService.getDependencySuite(id);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group/dependency/add", method = RequestMethod.GET)
    @ResponseBody
    public List<BatchGroupEntity> canAddDependency(HttpServletRequest request) {
        String batchId = request.getParameter("batch_id");
        String id = request.getParameter("id");
        logger.info("batch id is:{}, suite key is:{}", batchId, id);
        return batchGroupService.getAvaiableDependencySuite(batchId, id);
    }

    @RequestMapping(value = "/v1/dispatch/batch/define/group/dependency", method = RequestMethod.POST)
    @ResponseBody
    public String addDependency(HttpServletResponse response, HttpServletRequest request) {

        String json = request.getParameter("JSON");

        List<GroupDependencyEntity> list = new GsonBuilder().create().fromJson(json,
                new TypeToken<List<GroupDependencyEntity>>() {
                }.getType());
        RetMsg retMsg = batchGroupService.addGroupDependency(list);
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
        RetMsg retMsg = batchGroupService.deleteGroupDependency(uuid);
        if (!retMsg.checkCode()) {
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }
        return Hret.success(retMsg);
    }
}
