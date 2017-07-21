package com.asofdate.batch.dto;

import java.io.Serializable;

/**
 * Created by hzwy23 on 2017/7/16.
 */
public class BatchRunConfDto implements Serializable {
    private String domainId;
    private String batchId;
    private String asOfDate;
    private String redisSwitch;
    private String basePath;
    private String completeDate;
    private String paggingFreq;
    private String paggingFreqMult;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getRedisSwitch() {
        return redisSwitch;
    }

    public void setRedisSwitch(String redisSwitch) {
        this.redisSwitch = redisSwitch;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getPaggingFreq() {
        return paggingFreq;
    }

    public void setPaggingFreq(String paggingFreq) {
        this.paggingFreq = paggingFreq;
    }

    public String getPaggingFreqMult() {
        return paggingFreqMult;
    }

    public void setPaggingFreqMult(String paggingFreqMult) {
        this.paggingFreqMult = paggingFreqMult;
    }

    @Override
    public String toString() {
        return "BatchRunConfDto{" +
                "domainId='" + domainId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", asOfDate='" + asOfDate + '\'' +
                ", redisSwitch='" + redisSwitch + '\'' +
                ", basePath='" + basePath + '\'' +
                ", completeDate='" + completeDate + '\'' +
                ", paggingFreq='" + paggingFreq + '\'' +
                ", paggingFreqMult='" + paggingFreqMult + '\'' +
                '}';
    }
}
