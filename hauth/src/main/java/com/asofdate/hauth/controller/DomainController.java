package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.service.AuthService;
import com.asofdate.hauth.service.DomainService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.Validator;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
 * Created by hzwy23 on 2017/5/19.
 *
 * @author hzwy23
 */
@RestController
@RequestMapping(value = "/v1/auth/domain")
public class DomainController {
    private final Logger logger = LoggerFactory.getLogger(DomainController.class);

    @Autowired
    private DomainService domainService;
    @Autowired
    private AuthService authService;

    /**
     * 查询系统中所有的域信息
     * 如果用户属于超级管理域，则可以查看到超级管理域
     * 否则在结果中，去掉超级管理域后返回给客户端
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List getAll(HttpServletRequest request) {
        List<DomainEntity> list = domainService.getAll();
        String userDomainId = JwtService.getConnUser(request).getDomainID();
        if (Validator.isAdminDomain(userDomainId)) {
            return list;
        }

        //用户非超级管理域，过滤超级管理域信息
        for (int i = 0; i < list.size(); i++) {
            if (Validator.isAdminDomain(list.get(i).getDomain_id())) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    /**
     * 删除域信息
     * 在删除域的时候，会首先检查用户是否有权限对这个域进行写入权限
     * 只有拥有了写入权限，才能删除这个域
     * 如果域中已经存在了用户，则无法被删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(HttpServletResponse response, HttpServletRequest request) {
        String jsonObj = request.getParameter("JSON");
        List<DomainEntity> list = new GsonBuilder().create().fromJson(jsonObj, new TypeToken<List<DomainEntity>>() {
        }.getType());
        for (DomainEntity m : list) {
            Boolean status = authService.domainAuth(request, m.getDomain_id(), "w").getStatus();
            if (!status) {
                response.setStatus(403);
                return Hret.error(403, "您没有权限删除域【 " + m.getDomain_desc() + " 】", null);
            }
        }
        RetMsg retMsg = domainService.delete(list);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    /**
     * 新增域
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(HttpServletResponse response, HttpServletRequest request) {
        DomainEntity domainEntity = parse(request);
        RetMsg retMsg = domainService.add(domainEntity);

        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }

        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    /**
     * 查询某一个域的详细信息
     * 如果http请求的参数domain_id为空，则返回null
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String getDomainDetails(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null || domainId.isEmpty()) {
            logger.info("domain id is empty, return null");
            return null;
        }

        // 检查用户对域有没有读权限
        Boolean status = authService.domainAuth(request, domainId, "r").getStatus();
        if (status) {
            return Hret.error(403, "您没有被授权访问域【" + domainId + "】", null);
        }

        DomainEntity domainEntity = domainService.getDomainDetails(domainId);

        return new GsonBuilder().create().toJson(domainEntity);
    }


    /**
     * 更新域信息
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletResponse response, HttpServletRequest request) {
        DomainEntity domainEntity = parse(request);
        Boolean status = authService.domainAuth(request, domainEntity.getDomain_id(), "w").getStatus();
        if (!status) {
            response.setStatus(403);
            return Hret.error(403, "你没有权限编辑域 [ " + domainEntity.getDomain_desc() + " ]", domainEntity);
        }

        RetMsg retMsg = domainService.update(domainEntity);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }

        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);

    }

    private DomainEntity parse(HttpServletRequest request) {
        DomainEntity domainEntity = new DomainEntity();
        domainEntity.setDomain_id(request.getParameter("domain_id"));
        domainEntity.setDomain_desc(request.getParameter("domain_desc"));
        domainEntity.setDomain_status_cd(request.getParameter("domain_status_cd"));
        domainEntity.setDomain_status_id(request.getParameter("domain_status_id"));
        String userId = JwtService.getConnUser(request).getUserId();
        domainEntity.setDomain_modify_user(userId);
        domainEntity.setCreate_user_id(userId);
        return domainEntity;
    }
}
