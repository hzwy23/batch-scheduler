package com.asofdate.batch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/30.
 */
public class GroupArgumentEntity {
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("jobKey")
    private String jobKey;
    @JsonProperty("arg_id")
    private String arg_id;
    @JsonProperty("arg_value")
    private String arg_value;
    @JsonProperty("domain_id")
    private String domain_id;
    @JsonProperty("group_id")
    private String group_id;
    @JsonProperty("task_id")
    private String task_id;

    public String getGroupId() {
        return group_id;
    }

    public void setGroupId(String group_id) {
        this.group_id = group_id;
    }

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String task_id) {
        this.task_id = task_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String id) {
        this.jobKey = id;
    }

    public String getArgId() {
        return arg_id;
    }

    public void setArgId(String arg_id) {
        this.arg_id = arg_id;
    }

    public String getArgValue() {
        return arg_value;
    }

    public void setArgValue(String arg_value) {
        this.arg_value = arg_value;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "GroupArgumentEntity{" +
                "uuid='" + uuid + '\'' +
                ", jobKey='" + jobKey + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", arg_value='" + arg_value + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", task_id='" + task_id + '\'' +
                '}';
    }
}
