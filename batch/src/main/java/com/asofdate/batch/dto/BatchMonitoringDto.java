package com.asofdate.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/6/26.
 */
public class BatchMonitoringDto {
    @JsonProperty("as_of_date")
    private String as_of_date;
    private Float ratio;

    public String getAsOfDate() {
        return as_of_date;
    }

    public void setAsOfDate(String as_of_date) {
        this.as_of_date = as_of_date;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "BatchMonitoringDto{" +
                "as_of_date='" + as_of_date + '\'' +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}
