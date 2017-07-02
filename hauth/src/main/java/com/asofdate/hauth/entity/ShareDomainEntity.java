package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/6/19.
 */
public class ShareDomainEntity {
    public String uuid;
    public String domain_id;
    public String target_domain_id;
    public String domain_name;
    public String authorization_level;
    public String create_user;
    public String create_date;
    public String modify_user;
    public String modify_date;

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTarget_domain_id() {
        return target_domain_id;
    }

    public void setTarget_domain_id(String target_domainId) {
        this.target_domain_id = target_domainId;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getAuthorization_level() {
        return authorization_level;
    }

    public void setAuthorization_level(String authorization_level) {
        this.authorization_level = authorization_level;
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

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }
}
