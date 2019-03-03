package com.asofdate.hauth.vo;

import lombok.Data;


@Data
public class SysDomainAuthorizationVo {

    private String uuid;

    private String domainId;

    private String userId;

    private Integer authorizationLevel;

    private String createUser;

    private String modifyUser;

    private String createDate;

    private String modifyDate;

    private Boolean defaultDomain;

    private String userName;

    private String orgUnitId;

    private String orgUnitDesc;

}
