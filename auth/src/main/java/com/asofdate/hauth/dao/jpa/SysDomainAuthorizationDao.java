package com.asofdate.hauth.dao.jpa;

import com.asofdate.hauth.entity.SysDomainAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDomainAuthorizationDao extends JpaRepository<SysDomainAuthorization, String> {

    List<SysDomainAuthorization> findByUserId(String userId);

    @Query(value = "update SysDomainAuthorization t set t.defaultDomain = 0 where t.userId = ?1")
    int updateClearDefaultDomain(String userId);

    @Query(value = "update SysDomainAuthorization t set t.defaultDomain = 1 where t.uuid = ?1")
    int updateUserDefaultDomain(String uuid);

    int countByDomainIdAndUserId(String domainId, String userId);

    SysDomainAuthorization findByDomainIdAndUserId(String domainId, String userId);

    @Query(value = "update SysDomainAuthorization set authorizationLevel = ?1 where uuid = ?2")
    int updateLevel(String uuid, Integer level);

}
