package com.asofdate.batch.dto;

/**
 * Created by hzwy23 on 2017/7/17.
 */
public class JobKeyDepDto {
    public String jobKey;
    public String upJobKey;
    public String suiteKey;

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getUpJobKey() {
        return upJobKey;
    }

    public void setUpJobKey(String upJobKey) {
        this.upJobKey = upJobKey;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String suiteKey) {
        this.suiteKey = suiteKey;
    }

    @Override
    public String toString() {
        return "JobKeyDepDto{" +
                "jobKey='" + jobKey + '\'' +
                ", upJobKey='" + upJobKey + '\'' +
                ", suiteKey='" + suiteKey + '\'' +
                '}';
    }
}
