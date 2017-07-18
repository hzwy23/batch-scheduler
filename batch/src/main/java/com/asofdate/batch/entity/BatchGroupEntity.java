package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class BatchGroupEntity {
    public String batch_id;
    public String group_id;
    public String suiteKey;
    public String upSuiteKey;
    public String group_desc;
    public String code_number;
    public String domain_id;

    public String getUpSuiteKey() {
        return upSuiteKey;
    }

    public void setUpSuiteKey(String up_id) {
        this.upSuiteKey = up_id;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String id) {
        this.suiteKey = id;
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
                ", batch_id='" + batch_id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", id='" + suiteKey + '\'' +
                ", up_id='" + upSuiteKey + '\'' +
                ", group_desc='" + group_desc + '\'' +
                ", code_number='" + code_number + '\'' +
                ", domain_id='" + domain_id + '\'' +
                '}';
    }
}
