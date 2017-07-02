package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public class BatchGroupStatusEntity {
    public String batchId;
    public String gid;
    public String status;
    public String startTime;
    public String endTime;
    public String statusDesc;
    public String groupDesc;
    public Integer totalJobsCnt;
    public Integer completeJobsCnt;
    public Integer ratio;

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BatchGroupStatusEntity{" +
                "batchId='" + batchId + '\'' +
                ", gid='" + gid + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", totalJobsCnt=" + totalJobsCnt +
                ", completeJobsCnt=" + completeJobsCnt +
                ", ratio=" + ratio +
                '}';
    }
}
