package com.asofdate.hauth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/6/21.
 */
public class ThemeValueEntity {
    @JsonProperty("theme_id")
    private String theme_id;

    @JsonProperty("theme_desc")
    private String theme_desc;

    @JsonProperty("res_id")
    private String res_id;

    @JsonProperty("res_url")
    private String res_url;

    @JsonProperty("res_type")
    private String res_type;

    @JsonProperty("res_bg_color")
    private String res_bg_color;

    @JsonProperty("res_class")
    private String res_class;

    @JsonProperty("group_id")
    private String group_id;

    @JsonProperty("res_img")
    private String res_img;

    @JsonProperty("sort_id")
    private String sort_id;

    @JsonProperty("res_name")
    private String res_name;

    @JsonProperty("res_up_id")
    private String res_up_id;

    @JsonProperty("res_attr")
    private String res_attr;

    @JsonProperty("res_open_type")
    private String res_open_type;

    @JsonProperty("new_iframe")
    private String new_iframe;

    public String getNew_iframe() {
        return new_iframe;
    }

    public void setNew_iframe(String new_iframe) {
        this.new_iframe = new_iframe;
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

    public String getRes_attr() {
        return res_attr;
    }

    public void setRes_attr(String res_attr) {
        this.res_attr = res_attr;
    }

    public String getRes_open_type() {
        return res_open_type;
    }

    public void setRes_open_type(String res_open_type) {
        this.res_open_type = res_open_type;
    }

    public String getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(String theme_id) {
        this.theme_id = theme_id;
    }

    public String getTheme_desc() {
        return theme_desc;
    }

    public void setTheme_desc(String theme_desc) {
        this.theme_desc = theme_desc;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getRes_url() {
        return res_url;
    }

    public void setRes_url(String res_url) {
        this.res_url = res_url;
    }

    public String getRes_type() {
        return res_type;
    }

    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }

    public String getRes_bg_color() {
        return res_bg_color;
    }

    public void setRes_bg_color(String res_bg_color) {
        this.res_bg_color = res_bg_color;
    }

    public String getRes_class() {
        return res_class;
    }

    public void setRes_class(String res_class) {
        this.res_class = res_class;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getRes_img() {
        return res_img;
    }

    public void setRes_img(String res_img) {
        this.res_img = res_img;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }
}
