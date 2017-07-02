package com.asofdate.batch.entity;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public class GroupDependencyEntity {
    public String uuid;
    public String id;
    public String up_id;
    public String domain_id;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpId() {
        return up_id;
    }

    public void setUpId(String up_id) {
        this.up_id = up_id;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    @Override
    public String toString() {
        return "GroupDependencyEntity{" +
                "uuid='" + uuid + '\'' +
                ", id='" + id + '\'' +
                ", up_id='" + up_id + '\'' +
                ", domain_id='" + domain_id + '\'' +
                '}';
    }
}
