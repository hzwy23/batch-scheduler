package com.asofdate.hauth.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_domain_authorization")
public class SysDomainAuthorization {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "domain_id")
    private String domainId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "Authorization_level")
    private Integer authorizationLevel;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "modify_user")
    private String modifyUser;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "modify_date")
    private String modifyDate;

    @Column(name = "default_domain")
    private Boolean defaultDomain;
}
