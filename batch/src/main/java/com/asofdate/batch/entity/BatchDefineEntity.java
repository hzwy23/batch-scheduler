package com.asofdate.batch.entity;

import com.asofdate.utils.TimeFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class BatchDefineEntity {
    @JsonProperty("batch_id")
    private String batch_id;
    @JsonProperty("code_number")
    private String code_number;
    @JsonProperty("batch_desc")
    private String batch_desc;
    @JsonProperty("batch_status")
    private String batch_status;
    @JsonProperty("as_of_date")
    private String as_of_date;
    @JsonProperty("start_date")
    private String start_date;
    @JsonProperty("ret_msg")
    private String ret_msg;
    @JsonProperty("complete_date")
    private String complete_date;
    @JsonProperty("end_date")
    private String end_date;
    @JsonProperty("domain_id")
    private String domain_id;
    @JsonProperty("batch_status_desc")
    private String batch_status_desc;
    @JsonProperty("pagging_freq")
    private String pagging_freq;
    @JsonProperty("pagging_freq_mult")
    private String pagging_freq_mult;
    @JsonProperty("pagging_freq_mult_desc")
    private String pagging_freq_mult_desc;

    public String getPaggingFreq() {
        return pagging_freq;
    }

    public void setPaggingFreq(String pagging_freq) {
        this.pagging_freq = pagging_freq;
    }

    public String getPaggingFreqMult() {
        return pagging_freq_mult;
    }

    public void setPaggingFreqMult(String pagging_freq_mult) {
        this.pagging_freq_mult = pagging_freq_mult;
    }

    public String getPaggingFreqMultDesc() {
        return pagging_freq_mult_desc;
    }

    public void setPaggingFreqMultDesc(String pagging_freq_mult_desc) {
        this.pagging_freq_mult_desc = pagging_freq_mult_desc;
    }

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
        this.as_of_date = TimeFormat.formatTime(as_of_date);
    }

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = TimeFormat.formatTime(start_date);
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
        this.complete_date = TimeFormat.formatTime(complete_date);
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = TimeFormat.formatTime(end_date);
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
                ", pagging_freq='" + pagging_freq + '\'' +
                ", pagging_freq_mult='" + pagging_freq_mult + '\'' +
                ", pagging_freq_mult_desc='" + pagging_freq_mult_desc + '\'' +
                '}';
    }
}
