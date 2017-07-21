package com.asofdate.batch.dto;

/**
 * Created by hzwy23 on 2017/7/19.
 */
public class ProcListDto {
    private String procName;
    private String procDesc;
    private String procUpId;
    private String procAttr;

    public String getProcAttr() {
        return procAttr;
    }

    public void setProcAttr(String procAttr) {
        this.procAttr = procAttr;
    }

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
}
