package com.asofdate.batch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class GroupDefineEntity {
    @JsonProperty("group_id")
    private String group_id;
    @JsonProperty("code_number")
    private String code_number;
    @JsonProperty("group_desc")
    private String group_desc;
    @JsonProperty("create_user")
    private String create_user;
    @JsonProperty("create_date")
    private String create_date;
    @JsonProperty("modify_user")
    private String modify_user;
    @JsonProperty("modify_date")
    private String modify_date;
    @JsonProperty("domain_id")
    private String domain_id;

    public String getGroupId() {
        return group_id;
    }

    public void setGroupId(String group_id) {
        this.group_id = group_id;
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getGroupDesc() {
        return group_desc;
    }

    public void setGroupDesc(String group_desc) {
        this.group_desc = group_desc;
    }

    public String getCreateUser() {
        return create_user;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public String getCreateDate() {
        return create_date;
    }

    public void setCreateDate(String create_date) {
        this.create_date = create_date;
    }

    public String getModifyUser() {
        return modify_user;
    }

    public void setModifyUser(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getModifyDate() {
        return modify_date;
    }

    public void setModifyDate(String modify_date) {
        this.modify_date = modify_date;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "GroupDefineEntity{" +
                "group_id='" + group_id + '\'' +
                ", code_number='" + code_number + '\'' +
                ", group_desc='" + group_desc + '\'' +
                ", create_user='" + create_user + '\'' +
                ", create_date='" + create_date + '\'' +
                ", modify_user='" + modify_user + '\'' +
                ", modify_date='" + modify_date + '\'' +
                ", domain_id='" + domain_id + '\'' +
                '}';
    }
}
