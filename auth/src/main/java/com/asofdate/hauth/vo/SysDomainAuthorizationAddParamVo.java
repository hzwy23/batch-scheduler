package com.asofdate.hauth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class SysDomainAuthorizationAddParamVo {

    @ApiModelProperty(name = "项目ID")
    @NotBlank(message = "请选择项目")
    private String domainId;

    @ApiModelProperty(name = "用户ID")
    @NotBlank(message = "请选择账号")
    private String userId;

    @ApiModelProperty(name = "授权模式")
    @NotNull(message = "请选择授权模式")
    private Integer authorizationLevel;
}
