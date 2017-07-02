package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/6/1.
 */
public class DomainShareEntity {
    public String domain_id;
    public String target_domain_id;
    public String authorization_level;

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getTarget_domain_id() {
        return target_domain_id;
    }

    public void setTarget_domain_id(String target_domain_id) {
        this.target_domain_id = target_domain_id;
    }

    public String getAuthorization_level() {
        return authorization_level;
    }

    public void setAuthorization_level(String authorization_level) {
        this.authorization_level = authorization_level;
    }
}
