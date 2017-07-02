package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public class DomainEntity {
    public String domain_id;
    public String domain_desc;
    public String domain_status_id;
    public String domain_status_desc;

    public String domain_status;
    public String maintance_date;
    public String create_user_id;
    public String domain_modify_date;
    public String domain_modify_user;
    public String domain_status_cd;

    public String getDomain_status_cd() {
        return domain_status_cd;
    }

    public void setDomain_status_cd(String domain_status_cd) {
        this.domain_status_cd = domain_status_cd;
    }

    public String getMaintance_date() {
        return maintance_date;
    }

    public void setMaintance_date(String maintance_date) {
        this.maintance_date = maintance_date;
    }

    public String getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(String create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getDomain_modify_date() {
        return domain_modify_date;
    }

    public void setDomain_modify_date(String domain_modify_date) {
        this.domain_modify_date = domain_modify_date;
    }

    public String getDomain_modify_user() {
        return domain_modify_user;
    }

    public void setDomain_modify_user(String domain_modify_user) {
        this.domain_modify_user = domain_modify_user;
    }

    public String getDomain_status() {
        return domain_status;
    }

    public void setDomain_status(String domain_status) {
        this.domain_status = domain_status;
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

    public String getDomain_status_id() {
        return domain_status_id;
    }

    public void setDomain_status_id(String domain_status_id) {
        this.domain_status_id = domain_status_id;
    }

    public String getDomain_status_desc() {
        return domain_status_desc;
    }

    public void setDomain_status_desc(String domain_status_desc) {
        this.domain_status_desc = domain_status_desc;
    }
}
