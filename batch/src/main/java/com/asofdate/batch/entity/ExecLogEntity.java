package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/7/13.
 */
public class ExecLogEntity {
    public String jobId;
    public String message;
    public String execTime;
    public int sortId;
    public String batchId;
    public String sid;
    public String asOfDate;

    public String getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(String asOfDate) {
        this.asOfDate = asOfDate;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String id) {
        this.sid = id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String eid) {
        this.jobId = eid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    @Override
    public String toString() {
        return "ExecLogEntity{" +
                "eid='" + jobId + '\'' +
                ", message='" + message + '\'' +
                ", execTime='" + execTime + '\'' +
                ", sortId=" + sortId +
                ", batchId='" + batchId + '\'' +
                ", id='" + sid + '\'' +
                '}';
    }
}
