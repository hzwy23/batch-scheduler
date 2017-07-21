package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/18.
 */
public class UserDetailsEntity {
    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("user_name")
    private String user_name;

    @JsonProperty("status_desc")
    private String status_desc;

    @JsonProperty("user_create_date")
    private String user_create_date;

    @JsonProperty("user_owner")
    private String user_owner;

    @JsonProperty("user_email")
    private String user_email;

    @JsonProperty("user_phone")
    private String user_phone;

    @JsonProperty("org_unit_id")
    private String org_unit_id;

    @JsonProperty("org_unit_desc")
    private String org_unit_desc;

    @JsonProperty("domain_id")
    private String domain_id;

    @JsonProperty("domain_name")
    private String domain_name;

    @JsonProperty("user_maintance_date")
    private String user_maintance_date;

    @JsonProperty("user_maintance_user")
    private String user_maintance_user;

    @JsonProperty("status_id")
    private String status_id;

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getStatusDesc() {
        return status_desc;
    }

    public void setStatusDesc(String status_desc) {
        this.status_desc = status_desc;
    }

    public String getUserCreateDate() {
        return user_create_date;
    }

    public void setUserCreateDate(String user_create_date) {
        this.user_create_date = user_create_date;
    }

    public String getUserOwner() {
        return user_owner;
    }

    public void setUserOwner(String user_owner) {
        this.user_owner = user_owner;
    }

    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public String getUserPhone() {
        return user_phone;
    }

    public void setUserPhone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getOrgUnitId() {
        return org_unit_id;
    }

    public void setOrgUnitId(String org_unit_id) {
        this.org_unit_id = org_unit_id;
    }

    public String getOrgUnitDesc() {
        return org_unit_desc;
    }

    public void setOrgUnitDesc(String org_unit_desc) {
        this.org_unit_desc = org_unit_desc;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getDomainName() {
        return domain_name;
    }

    public void setDomainName(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getUserMaintanceDate() {
        return user_maintance_date;
    }

    public void setUserMaintanceDate(String user_maintance_date) {
        this.user_maintance_date = user_maintance_date;
    }

    public String getUserMaintanceUser() {
        return user_maintance_user;
    }

    public void setUserMaintanceUser(String user_maintance_user) {
        this.user_maintance_user = user_maintance_user;
    }

    public String getStatusId() {
        return status_id;
    }

    public void setStatusId(String status_id) {
        this.status_id = status_id;
    }
}
