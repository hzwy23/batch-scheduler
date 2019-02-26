package com.asofdate.hauth.controller;

import com.asofdate.hauth.service.SysDomainAuthorizationService;
import com.asofdate.hauth.vo.SysDomainAuthorizationAddParamVo;
import com.asofdate.utils.RetMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping(value = "/v1/auth/grant/domain")
@Api("项目授权管理")
public class SysDomainGrantController {

    @Autowired
    private SysDomainAuthorizationService sysDomainAuthorizationService;

    @ApiOperation(value = "查询用户能够访问的域")
    @RequestMapping(value = "/v1/auth/user/domain/grant", method = RequestMethod.GET)
    public RetMsg findUserDomains(){
        return RetMsg.success();
    }

    @ApiOperation(value = "删除授权信息")
    @RequestMapping(value = "/v1/auth/user/domain/grant/{uuid}", method = RequestMethod.DELETE)
    public RetMsg delete(@PathVariable(value = "uuid") String uuid){
        return RetMsg.success();
    }

    @ApiOperation(value = "更新用户授权权限模式")
    @RequestMapping(value = "/v1/auth/user/domain/grant", method = RequestMethod.PUT)
    public RetMsg updateAuthorizationLevel(@RequestParam(value = "uuid") String uuid,
                                           @RequestParam(value = "level") Integer level){
        return RetMsg.success();
    }

    @ApiOperation(value = "添加授权信息")
    @RequestMapping(value = "/v1/auth/user/domain/grant", method = RequestMethod.POST)
    public RetMsg add(@Validated @RequestBody SysDomainAuthorizationAddParamVo paramVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return new RetMsg(10020,errorMessage, paramVo);
        }

        return RetMsg.success();
    }

    @ApiOperation(value = "查询未授权项目")
    public RetMsg findUnauth(){
        return RetMsg.success();
    }

}
