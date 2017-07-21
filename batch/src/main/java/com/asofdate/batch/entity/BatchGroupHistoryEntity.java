package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public class BatchGroupHistoryEntity {
    private String sid;
    private String suiteKey;
    private String status;
    private String statusDesc;
    private String startTime;
    private String endTime;
    private String groupDesc;
    private Integer totalJobsCnt;
    private Integer completeJobsCnt;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    @Override
    public String toString() {
        return "BatchGroupHistoryEntity{" +
                "sid='" + sid + '\'' +
                ", suiteKey='" + suiteKey + '\'' +
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
