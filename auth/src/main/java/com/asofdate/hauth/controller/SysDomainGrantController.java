package com.asofdate.hauth.controller;

import com.asofdate.hauth.authentication.JwtService;
import com.asofdate.hauth.dto.RequestUserDto;
import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.service.SysDomainAuthorizationService;
import com.asofdate.hauth.vo.SysDomainAuthorizationAddParamVo;
import com.asofdate.hauth.vo.SysDomainAuthorizationVo;
import com.asofdate.utils.RetMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@Api(description = "系统管理--项目授权管理")
@Slf4j
public class SysDomainGrantController {

    @Autowired
    private SysDomainAuthorizationService sysDomainAuthorizationService;

    @ApiOperation(value = "查询用户能够访问的域")
    @RequestMapping(value = "/v1/auth/user/domain/grant", method = RequestMethod.GET)
    public RetMsg findUserDomains(HttpServletRequest request) {
        RequestUserDto users = JwtService.getConnUser(request);
        String userId = users.getUserId();
        List<SysDomainAuthorizationVo> ret = sysDomainAuthorizationService.findAll(userId);
        return RetMsg.success(ret);
    }

    @RequestMapping(value = "/v1/auth/domain/unauth/user", method = RequestMethod.GET)
    public RetMsg findUnauthUsers(@RequestParam(value = "domainId") String domainId){

        List<SysDomainAuthorizationVo> ret = sysDomainAuthorizationService.findUnauahtUserByDomainId(domainId);
        return RetMsg.success(ret);
    }

    @RequestMapping(value = "/v1/auth/domain/grant/user", method = RequestMethod.GET)
    public RetMsg findAuthUsers(@RequestParam(value = "domainId") String domainId) {
        List<SysDomainAuthorizationVo> ret = sysDomainAuthorizationService.findByDomainId(domainId);
        log.debug("授予这个项目权限的用户是：{}", ret);
        return RetMsg.success(ret);
    }

    @ApiOperation(value = "删除授权信息")
    @RequestMapping(value = "/v1/auth/user/domain/grant/{uuid}", method = RequestMethod.DELETE)
    public RetMsg delete(@PathVariable(value = "uuid") String uuid) {
        return sysDomainAuthorizationService.revoke(uuid);
    }

    @ApiOperation(value = "批量删除授权信息")
    @RequestMapping(value = "/v1/auth/user/domain/revoke", method = RequestMethod.POST)
    public RetMsg batchDelete(@RequestParam(value = "uuids") List<String> uuids) {
        return sysDomainAuthorizationService.batchRevoke(uuids);
    }

    @ApiOperation(value = "更新用户授权权限模式")
    @RequestMapping(value = "/v1/auth/user/domain/grant", method = RequestMethod.PUT)
    public RetMsg updateAuthorizationLevel(@RequestParam(value = "uuid") String uuid,
                                           @RequestParam(value = "level") Integer level) {
        log.info("uuid 是：{}，授权模式是：{}", uuid, level);
        return sysDomainAuthorizationService.modify(uuid, level);
    }

    @ApiOperation(value = "添加授权信息")
    @RequestMapping(value = "/v1/auth/user/domain/grant", method = RequestMethod.POST)
    public RetMsg add(@Validated @RequestBody List<SysDomainAuthorizationAddParamVo> paramVo,
                      BindingResult bindingResult,
                      HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new RetMsg(10020, errorMessage, paramVo);
        }
        RequestUserDto user = JwtService.getConnUser(request);
        return sysDomainAuthorizationService.grant(paramVo, user.getUserId());
    }

    @ApiOperation(value = "查询未授权项目")
    @RequestMapping(value = "/v1/auth/user/domain/unauthorized", method = RequestMethod.GET)
    public RetMsg findUnauth(@RequestParam(value = "userId") String userId) {
        List<DomainEntity> ret = sysDomainAuthorizationService.findUnauth(userId);
        return RetMsg.success(ret);
    }

}
