package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public class RoleEntity {
    public String code_number;
    public String role_name;
    public String create_user;
    public String create_date;
    public String role_status_desc;
    public String role_status_code;
    public String domain_id;
    public String domain_desc;
    public String modify_date;
    public String modify_user;
    public String role_id;

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

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getDomain_desc() {
        return domain_desc;
    }

    public void setDomain_desc(String domain_desc) {
        this.domain_desc = domain_desc;
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
                ", domain_id='" + domain_id + '\'' +
                ", domain_desc='" + domain_desc + '\'' +
                ", modify_date='" + modify_date + '\'' +
                ", modify_user='" + modify_user + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
