package com.asofdate.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/7/7.
 */
public class GroupDefineDto {

    private String jobKey;

    @JsonProperty("arg_id")
    private String arg_id;

    @JsonProperty("arg_value")
    private String arg_value;

    @JsonProperty("domain_id")
    private String domain_id;


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

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String id) {
        this.jobKey = id;
    }

    @Override
    public String toString() {
        return "GroupDefineDto{" +
                "id='" + jobKey + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", arg_value='" + arg_value + '\'' +
                ", domain_id='" + domain_id + '\'' +
                '}';
    }
}
