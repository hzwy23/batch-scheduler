package com.asofdate.hauth.entity;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public class HandleLogEntity {
    public String uuid;
    public String user_id;
    public String handle_time;
    public String client_ip;
    public String status_code;
    public String method;
    public String url;
    public String data;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHandle_time() {
        return handle_time;
    }

    public void setHandle_time(String handle_time) {
        this.handle_time = handle_time;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
