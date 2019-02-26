package com.asofdate.hauth.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Data
public class DomainEntity {
    @Size(min = 1, max = 30, message = "域编码长度必须由1-30位字母、数字组成")
    private String domain_id;

    @Size(min = 1, max = 100, message = "域名称必须由1-100位字母、汉字、数字等组成")
    private String domain_desc;

    @NotBlank(message = "状态不能为空")
    private String domain_status_id;

    private String domain_status_desc;

    private String domain_status;

    private String maintance_date;

    private String create_user_id;

    private String domain_modify_date;

    private String domain_modify_user;

    private String domain_status_cd;

}
