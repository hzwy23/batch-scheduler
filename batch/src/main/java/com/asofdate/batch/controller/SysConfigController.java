package com.asofdate.batch.controller;

import com.asofdate.batch.dto.ProcListDto;
import com.asofdate.batch.dto.ScriptListDto;
import com.asofdate.batch.entity.ProcEntity;
import com.asofdate.batch.service.SysConfigService;
import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.utils.Hret;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/6/15.
 */
@RestController
@Api(description ="批次调度-调度核心参数管理")
public class SysConfigController {
    private final Logger logger = LoggerFactory.getLogger(SysConfigController.class);
    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation(value = "获取所有的系统参数配置信息", notes = "查询系统中已经定义的系统配置参数，不同的域可以对同一个参数定义不同的参数值")
    @ApiImplicitParam(required = true, name = "domain_id", value = "域编码")
    @RequestMapping(value = "/v1/dispatch/config/sys", method = RequestMethod.GET)
    public List getALL(@RequestParam("domain_id") String domainId, HttpServletRequest request) {

        if (domainId == null || domainId.isEmpty()) {
            domainId = JwtService.getConnUser(request).getDomainID();
        }

        return sysConfigService.findAll(domainId);
    }

    @ApiOperation(value = "更新系统参数", notes = "更新结果只对当前操作的域有效")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "domain_id", value = "域编码"),
            @ApiImplicitParam(required = true, name = "config_id", value = "参数编码"),
            @ApiImplicitParam(required = true, name = "config_value", value = "参数值"),
    })
    @RequestMapping(value = "/v1/dispatch/config/user", method = RequestMethod.POST)
    public String updateConfigValue(HttpServletResponse response, HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        String configId = request.getParameter("config_id");
        String configValue = request.getParameter("config_value");

        int size = sysConfigService.setValue(domainId, configId, configValue);
        if (size != 1) {
            response.setStatus(421);
            return Hret.error(421, "更新ETL调度系统核心参数失败", null);
        }
        return Hret.success(200, "success", null);
    }

    @ApiOperation(value = "查询任务基准目录下所有的可执行程序", notes = "返回基准路径下所有的文件信息，包括文件，目录，可执行程序等等")
    @ApiImplicitParam(required = true, name = "domain_id", value = "域编码")
    @RequestMapping(value = "/v1/dispatch/config/scripts", method = RequestMethod.GET)
    public String getScripts(HttpServletResponse response, HttpServletRequest request) {
        String domainId = request.getParameter("domain_id");
        String filePath = sysConfigService.getValue(domainId, "CONF0001");
        logger.debug(filePath);
        List<ScriptListDto> list = new ArrayList<>();
        getChild(list, filePath, filePath);
        return new GsonBuilder().create().toJson(list);
    }

    @ApiOperation(value = "查询数据库中所有的存储过程和函数")
    @RequestMapping(value = "/v1/dispatch/config/proc", method = RequestMethod.GET)
    public String getProcList(HttpServletResponse response, HttpServletRequest request) {
        List<ProcEntity> list = sysConfigService.getProcList();

        List<ProcListDto> procList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (ProcEntity m : list) {
            set.add(m.getProcName());
            ProcListDto row = new ProcListDto();
            row.setProcName(m.getProcName() + "." + m.getProcDesc());
            row.setProcDesc(m.getProcDesc());
            row.setProcUpId(m.getProcName());
            row.setProcAttr("0");
            procList.add(row);
        }

        for (String m : set) {
            ProcListDto row = new ProcListDto();
            row.setProcName(m);
            row.setProcDesc(m);
            row.setProcUpId("-1");
            row.setProcAttr("1");
            procList.add(row);
        }
        return new GsonBuilder().create().toJson(procList);
    }

    private void getChild(List<ScriptListDto> list, String filePath, String basePath) {
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        if (fileList == null || fileList.length == 0) {
            return;
        }
        for (File f : fileList) {
            String parentPath = f.getParent().replace(basePath, "");
            String curPath = f.getAbsolutePath().replace(basePath, "");
            String scriptType = f.isDirectory() ? "1" : "0";
            ScriptListDto dto = pack(f.getName(), curPath, parentPath, scriptType);
            list.add(dto);
            if (f.isDirectory()) {
                getChild(list, f.getPath(), basePath);
            }
        }
    }

    private ScriptListDto pack(String scriptName, String relativePath,
                               String parentPath, String scriptType) {
        ScriptListDto row = new ScriptListDto();
        row.setScriptName(scriptName);
        row.setRelativePath(relativePath);
        row.setParentPath(parentPath);
        row.setScriptType(scriptType);
        return row;
    }
}
