package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public class UserEntity {
    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("user_name")
    private String user_name;

    @JsonProperty("status_desc")
    private String status_desc;

    @JsonProperty("create_date")
    private String create_date;

    @JsonProperty("create_user")
    private String create_user;

    @JsonProperty("user_email")
    private String user_email;

    @JsonProperty("user_phone")
    private String user_phone;

    @JsonProperty("org_unit_id")
    private String org_unit_id;

    @JsonProperty("org_unit_desc")
    private String org_unit_desc;

    @JsonProperty("modify_date")
    private String modify_date;

    @JsonProperty("modify_user")
    private String modify_user;

    @JsonProperty("status_cd")
    private String status_cd;

    @JsonProperty("user_passwd")
    private String user_passwd;

    @JsonProperty("user_passwd_confirm")
    private String user_passwd_confirm;

    @JsonProperty("user_status")
    private String user_status;

    public String getUserStatus() {
        return user_status;
    }

    public void setUserStatus(String user_status) {
        this.user_status = user_status;
    }

    public String getUserPasswd() {
        return user_passwd;
    }

    public void setUserPasswd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public String getUserPasswdConfirm() {
        return user_passwd_confirm;
    }

    public void setUserPasswdConfirm(String user_passwd_confirm) {
        this.user_passwd_confirm = user_passwd_confirm;
    }

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

    public String getCreateDate() {
        return create_date;
    }

    public void setCreateDate(String create_date) {
        this.create_date = create_date;
    }

    public String getCreateUser() {
        return create_user;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
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

    public String getModifyDate() {
        return modify_date;
    }

    public void setModifyDate(String modify_date) {
        this.modify_date = modify_date;
    }

    public String getModifyUser() {
        return modify_user;
    }

    public void setModifyUser(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getStatusCd() {
        return status_cd;
    }

    public void setStatusCd(String status_cd) {
        this.status_cd = status_cd;
    }
}
