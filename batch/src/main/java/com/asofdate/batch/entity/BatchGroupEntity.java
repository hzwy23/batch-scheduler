package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class BatchGroupEntity {
    public String uuid;
    public String batch_id;
    public String group_id;
    public String id;
    public String up_id;
    public String group_desc;
    public String code_number;
    public String domain_id;

    public String getUpId() {
        return up_id;
    }

    public void setUpId(String up_id) {
        this.up_id = up_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupDesc() {
        return group_desc;
    }

    public void setGroupDesc(String group_desc) {
        this.group_desc = group_desc;
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBatchId() {
        return batch_id;
    }

    public void setBatchId(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getGroupId() {
        return group_id;
    }

    public void setGroupId(String group_id) {
        this.group_id = group_id;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "BatchGroupEntity{" +
                "uuid='" + uuid + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", id='" + id + '\'' +
                ", up_id='" + up_id + '\'' +
                ", group_desc='" + group_desc + '\'' +
                ", code_number='" + code_number + '\'' +
                ", domain_id='" + domain_id + '\'' +
                '}';
    }
}
