package com.asofdate.batch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class GroupTaskEntity {
    @JsonProperty("group_id")
    private String group_id;

    @JsonProperty("task_id")
    private String task_id;

    @JsonProperty("domain_id")
    private String domain_id;

    @JsonProperty("task_desc")
    private String task_desc;

    private String jobKey;

    @JsonProperty("code_number")
    private String code_number;

    private String upJobKey;

    @JsonProperty("task_type")
    private String task_type;

    @JsonProperty("task_type_desc")
    private String task_type_desc;


    public String getTaskType() {
        return task_type;
    }

    public void setTaskType(String task_type) {
        this.task_type = task_type;
    }

    public String getTaskTypeDesc() {
        return task_type_desc;
    }

    public void setTaskTypeDesc(String task_type_desc) {
        this.task_type_desc = task_type_desc;
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getUpJobKey() {
        return upJobKey;
    }

    public void setUpJobKey(String up_id) {
        this.upJobKey = up_id;
    }

    public String getTaskDesc() {
        return task_desc;
    }

    public void setTaskDesc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String id) {
        this.jobKey = id;
    }

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

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "GroupTaskEntity{" +
                ", group_id='" + group_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", task_desc='" + task_desc + '\'' +
                ", jobKey='" + jobKey + '\'' +
                ", code_number='" + code_number + '\'' +
                ", upJobKey='" + upJobKey + '\'' +
                ", task_type='" + task_type + '\'' +
                ", task_type_desc='" + task_type_desc + '\'' +
                '}';
    }
}
