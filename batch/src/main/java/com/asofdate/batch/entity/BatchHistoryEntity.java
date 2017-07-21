package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/16.
 */
public class BatchHistoryEntity {
    private String sid;
    private String batchId;
    private String batchDesc;
    private String batchStatus;
    private String batchStatusDesc;
    private String asOfDate;
    private String startTime;
    private String endTime;
    private String retMsg;
    private String domainId;
    private String codeNumber;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchDesc() {
        return batchDesc;
    }

    public void setBatchDesc(String batchDesc) {
        this.batchDesc = batchDesc;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getBatchStatusDesc() {
        return batchStatusDesc;
    }

    public void setBatchStatusDesc(String batchStatusDesc) {
        this.batchStatusDesc = batchStatusDesc;
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return "BatchHistoryEntity{" +
                "sid='" + sid + '\'' +
                ", batchId='" + batchId + '\'' +
                ", batchDesc='" + batchDesc + '\'' +
                ", batchStatus='" + batchStatus + '\'' +
                ", batchStatusDesc='" + batchStatusDesc + '\'' +
                ", asOfDate='" + asOfDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", domainId='" + domainId + '\'' +
                ", codeNumber='" + codeNumber + '\'' +
                '}';
    }
}
