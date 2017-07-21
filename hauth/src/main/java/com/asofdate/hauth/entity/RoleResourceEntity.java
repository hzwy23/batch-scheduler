package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/6/20.
 */
public class RoleResourceEntity {
    private String uuid;

    @JsonProperty("role_id")
    private String role_id;

    @JsonProperty("res_id")
    private String res_id;

    @JsonProperty("res_name")
    private String res_name;

    @JsonProperty("res_up_id")
    private String res_up_id;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_up_id() {
        return res_up_id;
    }

    public void setRes_up_id(String res_up_id) {
        this.res_up_id = res_up_id;
    }
}
