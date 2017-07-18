package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public class BatchJobHistoryEntity {
    public String sid;
    public String jobId;
    public String status;
    public String startTime;
    public String endTime;
    public String suiteKey;
    public String statusDesc;
    public String jobKey;
    public String taskId;
    public String taskDesc;
    public String taskType;
    public String taskTypeDesc;

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String tid) {
        this.jobKey = tid;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskTypeDesc() {
        return taskTypeDesc;
    }

    public void setTaskTypeDesc(String taskTypeDesc) {
        this.taskTypeDesc = taskTypeDesc;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String id) {
        this.sid = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String gid) {
        this.suiteKey = gid;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "BatchJobHistoryEntity{" +
                "sid='" + sid + '\'' +
                ", jobId='" + jobId + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", suiteKey='" + suiteKey + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                ", jobKey='" + jobKey + '\'' +
                ", taskId='" + taskId + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskTypeDesc='" + taskTypeDesc + '\'' +
                '}';
    }
}
