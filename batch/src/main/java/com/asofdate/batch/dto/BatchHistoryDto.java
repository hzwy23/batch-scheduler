package com.asofdate.batch.dto;


/**
 * Created by hzwy23 on 2017/6/27.
 */
public class BatchHistoryDto {
    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String uuid) {
        this.sid = uuid;
    }

    @Override
    public String toString() {
        return "BatchHistoryDto{" +
                "sid='" + sid + '\'' +
                '}';
    }
}
