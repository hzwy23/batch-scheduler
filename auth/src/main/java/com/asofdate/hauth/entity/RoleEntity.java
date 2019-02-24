package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public class RoleEntity {
    @JsonProperty("code_number")
    private String code_number;

    @JsonProperty("role_name")
    private String role_name;

    @JsonProperty("create_user")
    private String create_user;

    @JsonProperty("create_date")
    private String create_date;

    @JsonProperty("role_status_desc")
    private String role_status_desc;

    @JsonProperty("role_status_code")
    private String role_status_code;

    @JsonProperty("modify_date")
    private String modify_date;

    @JsonProperty("modify_user")
    private String modify_user;

    @JsonProperty("role_id")
    private String role_id;

    public String getCode_number() {
        return code_number;
    }

    public void setCode_number(String code_number) {
        this.code_number = code_number;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getRole_status_desc() {
        return role_status_desc;
    }

    public void setRole_status_desc(String role_status_desc) {
        this.role_status_desc = role_status_desc;
    }

    public String getRole_status_code() {
        return role_status_code;
    }

    public void setRole_status_code(String role_status_code) {
        this.role_status_code = role_status_code;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "code_number='" + code_number + '\'' +
                ", role_name='" + role_name + '\'' +
                ", create_user='" + create_user + '\'' +
                ", create_date='" + create_date + '\'' +
                ", role_status_desc='" + role_status_desc + '\'' +
                ", role_status_code='" + role_status_code + '\'' +
                ", modify_date='" + modify_date + '\'' +
                ", modify_user='" + modify_user + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
