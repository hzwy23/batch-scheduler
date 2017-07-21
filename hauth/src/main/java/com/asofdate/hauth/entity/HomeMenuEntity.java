package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/15.
 */

public class HomeMenuEntity {
    @JsonProperty("Res_id")
    private String Res_id;

    @JsonProperty("Res_name")
    private String Res_name;

    @JsonProperty("Res_url")
    private String Res_url;

    @JsonProperty("Res_bg_color")
    private String Res_bg_color;

    @JsonProperty("Res_class")
    private String Res_class;

    @JsonProperty("Res_img")
    private String Res_img;

    @JsonProperty("Group_id")
    private String Group_id;

    @JsonProperty("Res_up_id")
    private String Res_up_id;

    @JsonProperty("Res_open_type")
    private String Res_open_type;

    @JsonProperty("New_iframe")
    private String New_iframe;

    public String getNew_iframe() {
        return New_iframe;
    }

    public void setNew_iframe(String new_iframe) {
        New_iframe = new_iframe;
    }

    public String getRes_id() {
        return Res_id;
    }

    public void setRes_id(String res_id) {
        Res_id = res_id;
    }

    public String getRes_name() {
        return Res_name;
    }

    public void setRes_name(String res_name) {
        Res_name = res_name;
    }

    public String getRes_url() {
        return Res_url;
    }

    public void setRes_url(String res_url) {
        Res_url = res_url;
    }

    public String getRes_bg_color() {
        return Res_bg_color;
    }

    public void setRes_bg_color(String res_bg_color) {
        Res_bg_color = res_bg_color;
    }

    public String getRes_class() {
        return Res_class;
    }

    public void setRes_class(String res_class) {
        Res_class = res_class;
    }

    public String getRes_img() {
        return Res_img;
    }

    public void setRes_img(String res_img) {
        Res_img = res_img;
    }

    public String getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(String group_id) {
        Group_id = group_id;
    }

    public String getRes_up_id() {
        return Res_up_id;
    }

    public void setRes_up_id(String res_up_id) {
        Res_up_id = res_up_id;
    }

    public String getRes_open_type() {
        return Res_open_type;
    }

    public void setRes_open_type(String res_open_type) {
        Res_open_type = res_open_type;
    }
}
