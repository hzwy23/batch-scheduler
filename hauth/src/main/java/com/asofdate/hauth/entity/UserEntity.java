package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public class UserEntity {
    public String user_id;
    public String user_name;
    public String status_desc;
    public String create_date;
    public String create_user;
    public String user_email;
    public String user_phone;
    public String org_unit_id;
    public String org_unit_desc;
    public String domain_id;
    public String domain_name;
    public String modify_date;
    public String modify_user;
    public String status_cd;
    public String user_passwd;
    public String user_passwd_confirm;
    public String user_status;

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_passwd() {
        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public String getUser_passwd_confirm() {
        return user_passwd_confirm;
    }

    public void setUser_passwd_confirm(String user_passwd_confirm) {
        this.user_passwd_confirm = user_passwd_confirm;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus_desc() {
        return status_desc;
    }

    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getOrg_unit_id() {
        return org_unit_id;
    }

    public void setOrg_unit_id(String org_unit_id) {
        this.org_unit_id = org_unit_id;
    }

    public String getOrg_unit_desc() {
        return org_unit_desc;
    }

    public void setOrg_unit_desc(String org_unit_desc) {
        this.org_unit_desc = org_unit_desc;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
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

    public String getStatus_cd() {
        return status_cd;
    }

    public void setStatus_cd(String status_cd) {
        this.status_cd = status_cd;
    }
}
