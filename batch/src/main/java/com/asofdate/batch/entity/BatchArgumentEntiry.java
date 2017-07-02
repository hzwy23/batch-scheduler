package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class BatchArgumentEntiry {
    public String uuid;
    public String batch_id;
    public String arg_id;
    public String arg_desc;
    public String code_number;
    public String arg_value;
    public String domain_id;
    public String bind_as_of_date;

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

    public String getBindAsOfDate() {
        return bind_as_of_date;
    }

    public void setBindAsOfDate(String bind_as_of_date) {
        this.bind_as_of_date = bind_as_of_date;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
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

    @Override
    public String toString() {
        return "BatchArgumentEntiry{" +
                "uuid='" + uuid + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", arg_value='" + arg_value + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", bind_as_of_date='" + bind_as_of_date + '\'' +
                '}';
    }
}
