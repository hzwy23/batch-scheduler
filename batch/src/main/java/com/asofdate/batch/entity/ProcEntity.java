package com.asofdate.batch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/7/20.
 */
public class ProcEntity {
    @JsonProperty("procName")
    private String procName;

    @JsonProperty("procDesc")
    private String procDesc;

    @JsonProperty("procUpId")
    private String procUpId;

    @JsonProperty("procAttr")
    private String procAttr;

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcUpId() {
        return procUpId;
    }

    public void setProcUpId(String procUpId) {
        this.procUpId = procUpId;
    }

    public String getProcAttr() {
        return procAttr;
    }

    public void setProcAttr(String procAttr) {
        this.procAttr = procAttr;
    }

    @Override
    public String toString() {
        return "ProcEntity{" +
                "procName='" + procName + '\'' +
                ", procDesc='" + procDesc + '\'' +
                ", procUpId='" + procUpId + '\'' +
                ", procAttr='" + procAttr + '\'' +
                '}';
    }
}
