package com.asofdate.hauth.dto;

/**
 * Created by hzwy23 on 2017/6/27.
 */
public class RequestUserDTO {
    public String domainID;
    public String userId;
    public String orgID;

    public RequestUserDTO() {
    }

    public RequestUserDTO(String domainID, String userId, String orgID) {
        this.domainID = domainID;
        this.orgID = orgID;
        this.userId = userId;
    }

    public String getDomainID() {
        return domainID;
    }

    public void setDomainID(String domainID) {
        this.domainID = domainID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    @Override
    public String toString() {
        return "RequestUserDTO{" +
                "domainID='" + domainID + '\'' +
                ", userId='" + userId + '\'' +
                ", orgID='" + orgID + '\'' +
                '}';
    }
}
