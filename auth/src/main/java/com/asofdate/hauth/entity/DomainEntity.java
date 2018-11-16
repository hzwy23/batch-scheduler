package com.asofdate.hauth.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public class DomainEntity {
    @JsonProperty("domain_id")
    @Size(min = 1, max = 30, message = "域编码长度必须由1-30位字母、数字组成")
    private String domain_id;

    @JsonProperty("domain_desc")
    @Size(min = 1, max = 100, message = "域名称必须由1-100位字母、汉字、数字等组成")
    private String domain_desc;

    @JsonProperty("domain_status_id")
    @NotBlank(message = "请选择域状态")
    private String domain_status_id;

    @JsonProperty("domain_status_desc")
    private String domain_status_desc;

    @JsonProperty("domain_status")
    private String domain_status;

    @JsonProperty("maintance_date")
    private String maintance_date;

    @JsonProperty("create_user_id")
    private String create_user_id;

    @JsonProperty("domain_modify_date")
    private String domain_modify_date;

    @JsonProperty("domain_modify_user")
    private String domain_modify_user;

    @JsonProperty("domain_status_cd")
    private String domain_status_cd;

    public String getDomainStatusCd() {
        return domain_status_cd;
    }

    public void setDomainStatusCd(String domain_status_cd) {
        this.domain_status_cd = domain_status_cd;
    }

    public String getMaintanceDate() {
        return maintance_date;
    }

    public void setMaintanceDate(String maintance_date) {
        this.maintance_date = maintance_date;
    }

    public String getCreateUserId() {
        return create_user_id;
    }

    public void setCreateUserId(String create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getDomainModifyDate() {
        return domain_modify_date;
    }

    public void setDomainModifyDate(String domain_modify_date) {
        this.domain_modify_date = domain_modify_date;
    }

    public String getDomainModifyUser() {
        return domain_modify_user;
    }

    public void setDomainModifyUser(String domain_modify_user) {
        this.domain_modify_user = domain_modify_user;
    }

    public String getDomainStatus() {
        return domain_status;
    }

    public void setDomainStatus(String domain_status) {
        this.domain_status = domain_status;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domainId) {
        this.domain_id = domainId;
    }

    public String getDomainDesc() {
        return domain_desc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domain_desc = domainDesc;
    }

    public String getDomainStatusId() {
        return domain_status_id;
    }

    public void setDomainStatusId(String domain_status_id) {
        this.domain_status_id = domain_status_id;
    }

    public String getDomainStatusDesc() {
        return domain_status_desc;
    }

    public void setDomainStatusDesc(String domain_status_desc) {
        this.domain_status_desc = domain_status_desc;
    }

    @Override
    public String toString() {
        return "DomainEntity{" +
                "domain_id='" + domain_id + '\'' +
                ", domain_desc='" + domain_desc + '\'' +
                ", domain_status_id='" + domain_status_id + '\'' +
                ", domain_status_desc='" + domain_status_desc + '\'' +
                ", domain_status='" + domain_status + '\'' +
                ", maintance_date='" + maintance_date + '\'' +
                ", create_user_id='" + create_user_id + '\'' +
                ", domain_modify_date='" + domain_modify_date + '\'' +
                ", domain_modify_user='" + domain_modify_user + '\'' +
                ", domain_status_cd='" + domain_status_cd + '\'' +
                '}';
    }
}
