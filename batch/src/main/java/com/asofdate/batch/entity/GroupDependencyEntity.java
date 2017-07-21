package com.asofdate.batch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public class GroupDependencyEntity {
    private String uuid;
    private String suiteKey;
    private String upSuiteKey;
    @JsonProperty("domain_id")
    private String domain_id;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String id) {
        this.suiteKey = id;
    }

    public String getUpSuiteKey() {
        return upSuiteKey;
    }

    public void setUpSuiteKey(String up_id) {
        this.upSuiteKey = up_id;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "GroupDependencyEntity{" +
                "uuid='" + uuid + '\'' +
                ", suiteKey='" + suiteKey + '\'' +
                ", upSuiteKey='" + upSuiteKey + '\'' +
                ", domain_id='" + domain_id + '\'' +
                '}';
    }
}
