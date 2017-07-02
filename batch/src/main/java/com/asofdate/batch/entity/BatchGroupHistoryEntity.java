package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public class BatchGroupHistoryEntity {
    public String uuid;
    public String gid;
    public String status;
    public String statusDesc;
    public String startTime;
    public String endTime;
    public String groupDesc;
    public Integer totalJobsCnt;
    public Integer completeJobsCnt;

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

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    @Override
    public String toString() {
        return "BatchGroupHistoryEntity{" +
                "uuid='" + uuid + '\'' +
                ", gid='" + gid + '\'' +
                ", status='" + status + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", totalJobsCnt=" + totalJobsCnt +
                ", completeJobsCnt=" + completeJobsCnt +
                '}';
    }
}
