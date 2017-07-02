package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public class SysConfigEntity {
    public String configId;
    public String configDesc;
    public String configValue;
    public String image;
    public String details;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "SysConfigEntity{" +
                "configId='" + configId + '\'' +
                ", configDesc='" + configDesc + '\'' +
                ", configValue='" + configValue + '\'' +
                ", image='" + image + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
