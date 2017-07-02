package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/5/14.
 */
public class UserLoginEntity {

    public String username = "";

    public String password = "";

    public String statusId = "";

    public int continueErrorCnt = 0;

    public boolean flag = false;

    public String message = "success";

    public String retCode = "403";

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public int getContinueErrorCnt() {
        return continueErrorCnt;
    }

    public void setContinueErrorCnt(int continueErrorCnt) {
        this.continueErrorCnt = continueErrorCnt;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
