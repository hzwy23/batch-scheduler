package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.hauth.dto.AuthDTO;
import com.asofdate.hauth.entity.OrgEntity;
import com.asofdate.hauth.service.AuthService;
import com.asofdate.hauth.service.OrgService;
import com.asofdate.utils.Hret;
import com.asofdate.utils.JoinCode;
import com.asofdate.utils.RetMsg;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/v1/auth/org")
public class OrgController {
    @Autowired
    private AuthService authService;

    @Autowired
    private OrgService orgService;

    @RequestMapping(method = RequestMethod.GET)
    public List findAll(HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        if (domainId == null || domainId.isEmpty()) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }
        return orgService.findAll(domainId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sub")
    public List findSub(HttpServletResponse response, HttpServletRequest request) {
        String orgUnitId = request.getParameter("org_unit_id");
        String domainId = request.getParameter("domain_id");
        return orgService.findSub(domainId, orgUnitId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(HttpServletResponse response, HttpServletRequest request) {
        OrgEntity orgEntity = parse(request);
        RetMsg retMsg = orgService.add(orgEntity);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(HttpServletResponse response, HttpServletRequest request) {
        OrgEntity orgEntity = parse(request);
        RetMsg retMsg = orgService.update(orgEntity);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletResponse response, HttpServletRequest request) {
        String JSON = request.getParameter("JSON");
        JSONArray jsonArray = new JSONArray(JSON);
        List<OrgEntity> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject js = (JSONObject) jsonArray.get(i);
            String orgId = js.getString("org_id");
            String domainId = js.getString("domain_id");

            AuthDTO authDTO = authService.domainAuth(request, domainId, "w");
            if (!authDTO.getStatus()) {
                return Hret.error(403, "您没有权限删除域【" + domainId + "】中的机构信息", null);
            }
            OrgEntity entity = new OrgEntity();
            entity.setOrg_id(orgId);
            entity.setDomain_id(domainId);
            list.add(entity);
        }

        RetMsg retMsg = orgService.delete(list);
        if (retMsg.checkCode()) {
            return Hret.success(retMsg);
        }
        response.setStatus(retMsg.getCode());
        return Hret.error(retMsg);
    }

    private OrgEntity parse(HttpServletRequest request) {
        OrgEntity orgEntity = new OrgEntity();
        String codeNumber = request.getParameter("Org_unit_id");
        String domainId = request.getParameter("Domain_id");
        orgEntity.setCode_number(codeNumber);
        orgEntity.setOrg_desc(request.getParameter("Org_unit_desc"));
        orgEntity.setDomain_id(domainId);
        orgEntity.setUp_org_id(request.getParameter("Up_org_id"));
        String orgUnitId = JoinCode.join(domainId, codeNumber);
        orgEntity.setOrg_id(orgUnitId);
        String userId = JwtService.getConnUser(request).getUserId();
        orgEntity.setCreate_user(userId);
        orgEntity.setModify_user(userId);
        return orgEntity;
    }
}
