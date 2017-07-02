package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class GroupTaskEntity {
    public String uuid;
    public String group_id;
    public String task_id;
    public String domain_id;
    public String task_desc;
    public String id;
    public String code_number;
    public String up_id;

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getUpId() {
        return up_id;
    }

    public void setUpId(String up_id) {
        this.up_id = up_id;
    }

    public String getTaskDesc() {
        return task_desc;
    }

    public void setTaskDesc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
                "uuid='" + uuid + '\'' +
                ", group_id='" + group_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", task_desc='" + task_desc + '\'' +
                ", id='" + id + '\'' +
                ", code_number='" + code_number + '\'' +
                ", up_id='" + up_id + '\'' +
                '}';
    }
}
