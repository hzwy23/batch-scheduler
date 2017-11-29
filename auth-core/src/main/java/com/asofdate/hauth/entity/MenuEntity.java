package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public class MenuEntity {
    @JsonProperty("res_id")
    private String res_id;

    @JsonProperty("res_name")
    private String res_name;

    @JsonProperty("res_attr")
    private String res_attr;

    @JsonProperty("res_attr_desc")
    private String res_attr_desc;

    @JsonProperty("res_up_id")
    private String res_up_id;

    @JsonProperty("res_type")
    private String res_type;

    @JsonProperty("res_type_desc")
    private String res_type_desc;

    @JsonProperty("sys_flag")
    private String sys_flag;

    @JsonProperty("new_iframe")
    private String new_iframe;

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

    public String getRes_attr() {
        return res_attr;
    }

    public void setRes_attr(String res_attr) {
        this.res_attr = res_attr;
    }

    public String getRes_attr_desc() {
        return res_attr_desc;
    }

    public void setRes_attr_desc(String res_attr_desc) {
        this.res_attr_desc = res_attr_desc;
    }

    public String getRes_up_id() {
        return res_up_id;
    }

    public void setRes_up_id(String res_up_id) {
        this.res_up_id = res_up_id;
    }

    public String getRes_type() {
        return res_type;
    }

    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }

    public String getRes_type_desc() {
        return res_type_desc;
    }

    public void setRes_type_desc(String res_type_desc) {
        this.res_type_desc = res_type_desc;
    }

    public String getSys_flag() {
        return sys_flag;
    }

    public void setSys_flag(String sys_flag) {
        this.sys_flag = sys_flag;
    }
}
