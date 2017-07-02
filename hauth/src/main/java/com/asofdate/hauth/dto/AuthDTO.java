package com.asofdate.hauth.dto;

/**
 * Created by hzwy23 on 2017/6/29.
 */
public class AuthDTO {
    public Boolean status;
    public String message;

    public AuthDTO() {
    }

    public AuthDTO(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
