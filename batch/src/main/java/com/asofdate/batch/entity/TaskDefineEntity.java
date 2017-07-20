package com.asofdate.batch.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class TaskDefineEntity {

    @NotEmpty(message = "任务编码必须由1-30位字母、数字组成")
    @Size(min = 1,max = 30)
    public String taskId;

    public String codeNumber;

    @NotEmpty(message = "请输入任务详细描述信息")
    public String taskDesc;

    @NotEmpty(message = "请选择任务类型")
    public String taskType;
    public String taskTypeDesc;
    public String createUser;
    public String createDate;
    public String modifyDate;
    public String modifyUser;

    @NotEmpty(message = "域信息编码格式不正确，请联系管理员")
    public String domainId;

    @NotEmpty(message = "请输入脚本路径及名称")
    public String scriptFile;

    public String getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(String scriptFile) {
        this.scriptFile = scriptFile;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Override
    public String toString() {
        return "TaskDefineEntity{" +
                "taskId='" + taskId + '\'' +
                ", codeNumber='" + codeNumber + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskTypeDesc='" + taskTypeDesc + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", domainId='" + domainId + '\'' +
                ", scriptFile='" + scriptFile + '\'' +
                '}';
    }
}
