package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class TaskArgumentEntity {
    public String uuid;
    public String task_id;
    public String arg_id;
    public String arg_value;
    public String domain_id;
    public String sort_id;

    public String getSortId() {
        return sort_id;
    }

    public void setSortId(String sort_id) {
        this.sort_id = sort_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String task_id) {
        this.task_id = task_id;
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
        return "TaskArgumentEntity{" +
                "uuid='" + uuid + '\'' +
                ", task_id='" + task_id + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", arg_value='" + arg_value + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", sort_id='" + sort_id + '\'' +
                '}';
    }
}
