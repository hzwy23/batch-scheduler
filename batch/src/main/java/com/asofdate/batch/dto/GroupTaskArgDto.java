package com.asofdate.batch.dto;

/**
 * Created by hzwy23 on 2017/7/7.
 */
public class GroupTaskArgDto {
    public String uuid;
    public String task_id;
    public String arg_id;
    public String arg_type;
    public String arg_value;
    public String sort_id;
    public String domain_id;
    public String arg_type_desc;
    public String arg_desc;
    public String code_number;

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

    public String getArgType() {
        return arg_type;
    }

    public void setArgType(String arg_type) {
        this.arg_type = arg_type;
    }

    public String getArgValue() {
        return arg_value;
    }

    public void setArgValue(String arg_value) {
        this.arg_value = arg_value;
    }

    public String getSortId() {
        return sort_id;
    }

    public void setSortId(String sort_id) {
        this.sort_id = sort_id;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getArgTypeDesc() {
        return arg_type_desc;
    }

    public void setArgTypeDesc(String arg_type_desc) {
        this.arg_type_desc = arg_type_desc;
    }

    public String getArgDesc() {
        return arg_desc;
    }

    public void setArgDesc(String arg_desc) {
        this.arg_desc = arg_desc;
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    @Override
    public String toString() {
        return "GroupTaskArgDto{" +
                "uuid='" + uuid + '\'' +
                ", task_id='" + task_id + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", arg_type='" + arg_type + '\'' +
                ", arg_value='" + arg_value + '\'' +
                ", sort_id='" + sort_id + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", arg_type_desc='" + arg_type_desc + '\'' +
                ", arg_desc='" + arg_desc + '\'' +
                ", code_number='" + code_number + '\'' +
                '}';
    }
}
