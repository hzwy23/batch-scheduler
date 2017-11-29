package com.asofdate.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupTaskDto {
    private String id;
    private String left;
    private String top;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GroupTaskDto{" +
                "id='" + id + '\'' +
                ", left=" + left +
                ", top=" + top +
                ", text='" + text + '\'' +
                '}';
    }
}
