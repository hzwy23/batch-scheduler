package com.asofdate.hauth.dto;

/**
 * Created by hzwy23 on 2017/6/29.
 */
public class AuthDto {
    private Boolean status;
    private String message;

    public AuthDto() {
    }

    public AuthDto(Boolean status, String message) {
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
        return "AuthDto{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
