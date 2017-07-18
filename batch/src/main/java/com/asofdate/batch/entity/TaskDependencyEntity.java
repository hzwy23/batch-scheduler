package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public class TaskDependencyEntity {
    public String uuid;
    public String jobKey;
    public String upJobKey;
    public String domainId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getUpJobKey() {
        return upJobKey;
    }

    public void setUpJobKey(String up_id) {
        this.upJobKey = up_id;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domain_id) {
        this.domainId = domain_id;
    }

    @Override
    public String toString() {
        return "TaskDependencyEntity{" +
                "uuid='" + uuid + '\'' +
                ", jobKey='" + jobKey + '\'' +
                ", upJobKey='" + upJobKey + '\'' +
                ", domain_id='" + domainId + '\'' +
                '}';
    }
}
