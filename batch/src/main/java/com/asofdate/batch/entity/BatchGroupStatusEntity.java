package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public class BatchGroupStatusEntity {
    private String batchId;
    private String suiteKey;
    private String status;
    private String startTime;
    private String endTime;
    private String statusDesc;
    private String groupDesc;
    private Integer totalJobsCnt;
    private Integer completeJobsCnt;
    private Integer ratio;
    private String asOfDate;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String suiteKey) {
        this.suiteKey = suiteKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Integer getTotalJobsCnt() {
        return totalJobsCnt;
    }

    public void setTotalJobsCnt(Integer totalJobsCnt) {
        this.totalJobsCnt = totalJobsCnt;
    }

    public Integer getCompleteJobsCnt() {
        return completeJobsCnt;
    }

    public void setCompleteJobsCnt(Integer completeJobsCnt) {
        this.completeJobsCnt = completeJobsCnt;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
        this.asOfDate = asOfDate;
    }

    @Override
    public String toString() {
        return "BatchGroupStatusEntity{" +
                "batchId='" + batchId + '\'' +
                ", suiteKey='" + suiteKey + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", totalJobsCnt=" + totalJobsCnt +
                ", completeJobsCnt=" + completeJobsCnt +
                ", ratio=" + ratio +
                ", asOfDate='" + asOfDate + '\'' +
                '}';
    }
}
