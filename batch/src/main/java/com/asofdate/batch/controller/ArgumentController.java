package com.asofdate.batch.controller;

import com.asofdate.batch.entity.ArgumentDefineEntity;
import com.asofdate.batch.service.ArgumentDefineService;
import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
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
 * Created by hzwy23 on 2017/6/1.
 */
@RequestMapping(value = "/v1/dispatch/argument/define")
@RestController
@Api(description = "批次调度-参数配置管理")
public class ArgumentController {
    private final Logger logger = LoggerFactory.getLogger(ArgumentController.class);
    @Autowired
    private ArgumentDefineService argumentService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ArgumentDefineEntity> getArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }
        return argumentService.findAll(domainId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String postArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        ArgumentDefineEntity argumentDefine = parse(request);

        if (argumentDefine.getArgId().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "参数编码必须由1-30位字母、数字组成", null);
        }

        if (argumentDefine.getArgDesc().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请输入详细的参数描述信息", null);
        }

        if (argumentDefine.getArgType() == null) {
            response.setStatus(421);
            return Hret.error(421, "请选择参数类型", null);
        }

        if ("1".equals(argumentDefine.getArgType()) && argumentDefine.getArgValue().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请填写固定参数，参数值", null);
        }

        if ("4".equals(argumentDefine.getArgType()) && argumentDefine.getBindAsOfDate() == null) {
            response.setStatus(421);
            return Hret.error(421, "批次类型参数，请选择是否与数据日期绑定", null);
        }
        RetMsg retMsg = argumentService.addArgument(argumentDefine);
        if (SysStatus.SUCCESS_CODE == retMsg.getCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(421);
        return Hret.error(retMsg);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    @ResponseBody
    public String deleteArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        String json = request.getParameter("JSON");
        List<ArgumentDefineEntity> list = new GsonBuilder().create().fromJson(json, new TypeToken<List<ArgumentDefineEntity>>() {
        }.getType());
        RetMsg msg = argumentService.deleteArgument(list);
        if (SysStatus.SUCCESS_CODE == msg.getCode()) {
            return Hret.success(msg);
        }
        response.setStatus(421);
        return Hret.error(msg);
    }

    /*
     * 更新参数定义信息
     * */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String putArgumentDefine(HttpServletResponse response, HttpServletRequest request) {
        ArgumentDefineEntity argumentDefine = parse(request);

        if (argumentDefine.getArgId().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "参数编码必须由1-30位字母、数字组成", null);
        }

        if (argumentDefine.getArgDesc().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请输入详细的参数描述信息", null);
        }

        if (argumentDefine.getArgType() == null) {
            response.setStatus(421);
            return Hret.error(421, "请选择参数类型", null);
        }

        if ("1".equals(argumentDefine.getArgType()) && argumentDefine.getArgValue().isEmpty()) {
            response.setStatus(421);
            return Hret.error(421, "请填写固定参数，参数值", null);
        }

        if ("4".equals(argumentDefine.getArgType()) && argumentDefine.getBindAsOfDate() == null) {
            response.setStatus(421);
            return Hret.error(421, "批次类型参数，请选择是否与数据日期绑定", null);
        }
        RetMsg retMsg = argumentService.updateArgument(argumentDefine);
        if (SysStatus.SUCCESS_CODE == retMsg.getCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(421);
        return Hret.error(retMsg);
    }

    /*
     * 从客户单请求中,获取参数
     * 并转换成ArgumentDefineModel对象
     * */
    private ArgumentDefineEntity parse(HttpServletRequest request) {
        ArgumentDefineEntity argumentDefineEntity = new ArgumentDefineEntity();
        argumentDefineEntity.setArgId(request.getParameter("arg_id"));
        argumentDefineEntity.setArgValue(request.getParameter("arg_value"));
        argumentDefineEntity.setArgType(request.getParameter("arg_type"));
        argumentDefineEntity.setArgDesc(request.getParameter("arg_desc"));
        argumentDefineEntity.setDomainId(request.getParameter("domain_id"));
        argumentDefineEntity.setBindAsOfDate(request.getParameter("bind_as_of_date"));
        String userId = JwtService.getConnUser(request).getUserId();
        argumentDefineEntity.setCreateUser(userId);
        argumentDefineEntity.setModifyUser(userId);
        return argumentDefineEntity;
    }
}
