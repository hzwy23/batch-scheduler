package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.hauth.entity.ShareDomainEntity;
import com.asofdate.hauth.service.AuthService;
import com.asofdate.hauth.service.ShareDomainService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/19.
 */
@RestController
@RequestMapping(value = "/v1/auth/share/domain")
@Api("域共享配置管理")
public class ShareDomainController {
    private final Logger logger = LoggerFactory.getLogger(ShareDomainController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private ShareDomainService shareDomainService;

    @RequestMapping(method = RequestMethod.GET)
    public List findAll(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        return shareDomainService.findAll(domainId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(HttpServletResponse response, HttpServletRequest request) {
        ShareDomainEntity arg = parse(request);
        String domainId = arg.getDomain_id();
        Boolean status = authService.domainAuth(request, domainId, "w").getStatus();
        if (!status) {
            response.setStatus(403);
            return Hret.error(403, "您没有权限修改域[" + domainId + "]的授权信息", null);
        }
        RetMsg retMsg = shareDomainService.add(arg);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(HttpServletResponse response, HttpServletRequest request) {
        ShareDomainEntity arg = parse(request);
        String domainId = arg.getDomain_id();
        Boolean status = authService.domainAuth(request, domainId, "w").getStatus();
        if (!status) {
            response.setStatus(403);
            return Hret.error(403, "您没有权限修改域[" + domainId + "]的授权信息", null);
        }

        RetMsg retMsg = shareDomainService.update(arg);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletResponse response, HttpServletRequest request) {
        String args = request.getParameter("JSON");
        List<ShareDomainEntity> list;
        try {
            list = new GsonBuilder().create().fromJson(args,
                    new TypeToken<List<ShareDomainEntity>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            logger.info(e.getMessage());
            response.setStatus(SysStatus.EXCEPTION_ERROR_CODE);
            return Hret.error(RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, "参数异常，请联系管理员", e.getMessage()));
        }

        String domainId = request.getParameter("domain_id");
        Boolean status = authService.domainAuth(request, domainId, "w").getStatus();
        if (!status) {
            response.setStatus(403);
            return Hret.error(403, "您没有权限删除域[ " + domainId + " ]的授权信息", null);
        }

        RetMsg retMsg = shareDomainService.delete(list);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    @RequestMapping(value = "/unshare", method = RequestMethod.GET)
    public List unshareTarget(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        return shareDomainService.unShareTarget(domainId);
    }

    private ShareDomainEntity parse(HttpServletRequest request) {
        ShareDomainEntity shareDomainEntity = new ShareDomainEntity();
        shareDomainEntity.setDomain_id(request.getParameter("domain_id"));
        shareDomainEntity.setTarget_domain_id(request.getParameter("target_domain_id"));
        shareDomainEntity.setAuthorization_level(request.getParameter("authorization_level"));
        shareDomainEntity.setUuid(request.getParameter("uuid"));
        String userId = JwtService.getConnUser(request).getUserId();
        shareDomainEntity.setCreate_user(userId);
        shareDomainEntity.setModify_user(userId);
        return shareDomainEntity;
    }
}
