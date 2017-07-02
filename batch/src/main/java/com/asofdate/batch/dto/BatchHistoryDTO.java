package com.asofdate.batch.dto;

/**
 * Created by hzwy23 on 2017/6/27.
 */
public class BatchHistoryDTO {
    public String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "BatchHistoryDTO{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
