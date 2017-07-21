package com.asofdate.hauth.dto;

import com.asofdate.hauth.entity.DomainEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/30.
 */
public class DomainDto {
    @JsonProperty("domain_id")
    private String domain_id;

    @JsonProperty("owner_list")
    private List<DomainEntity> owner_list;

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    public List<DomainEntity> getOwnerList() {
        return owner_list;
    }

    public void setOwnerList(List<DomainEntity> owner_list) {
        this.owner_list = owner_list;
    }
}
