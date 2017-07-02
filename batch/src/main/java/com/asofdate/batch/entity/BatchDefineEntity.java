package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class BatchDefineEntity {
    public String batch_id;
    public String code_number;
    public String batch_desc;
    public String batch_status;
    public String as_of_date;
    public String start_date;
    public String ret_msg;
    public String complete_date;
    public String end_date;
    public String domain_id;
    public String batch_status_desc;

    public String getBatchStatusDesc() {
        return batch_status_desc;
    }

    public void setBatchStatusDesc(String batch_status_desc) {
        this.batch_status_desc = batch_status_desc;
    }

    public String getBatchId() {
        return batch_id;
    }

    public void setBatchId(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getBatchDesc() {
        return batch_desc;
    }

    public void setBatchDesc(String batch_desc) {
        this.batch_desc = batch_desc;
    }

    public String getBatchStatus() {
        return batch_status;
    }

    public void setBatchStatus(String batch_status) {
        this.batch_status = batch_status;
    }

    public String getAsOfDate() {
        return as_of_date;
    }

    public void setAsOfDate(String as_of_date) {
        this.as_of_date = as_of_date;
    }

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getRetMsg() {
        return ret_msg;
    }

    public void setRetMsg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getCompleteDate() {
        return complete_date;
    }

    public void setCompleteDate(String complete_date) {
        this.complete_date = complete_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "BatchDefineEntity{" +
                "batch_id='" + batch_id + '\'' +
                ", code_number='" + code_number + '\'' +
                ", batch_desc='" + batch_desc + '\'' +
                ", batch_status='" + batch_status + '\'' +
                ", as_of_date='" + as_of_date + '\'' +
                ", start_date='" + start_date + '\'' +
                ", ret_msg='" + ret_msg + '\'' +
                ", complete_date='" + complete_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", batch_status_desc='" + batch_status_desc + '\'' +
                '}';
    }
}
