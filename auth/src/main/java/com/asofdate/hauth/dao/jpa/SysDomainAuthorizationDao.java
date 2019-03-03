package com.asofdate.hauth.dao.jpa;

import com.asofdate.hauth.entity.SysDomainAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SysDomainAuthorizationDao extends JpaRepository<SysDomainAuthorization, String> {

    List<SysDomainAuthorization> findByUserId(String userId);

    @Query(value = "update SysDomainAuthorization t set t.defaultDomain = 0 where t.userId = ?1")
    @Modifying
    int updateClearDefaultDomain(String userId);

    @Query(value = "update SysDomainAuthorization t set t.defaultDomain = 1 where t.uuid = ?1")
    @Modifying
    int updateUserDefaultDomain(String uuid);

    int countByDomainIdAndUserId(String domainId, String userId);

    SysDomainAuthorization findByDomainIdAndUserId(String domainId, String userId);

    @Query(value = "update SysDomainAuthorization t set t.authorizationLevel = ?1 where t.uuid = ?2")
    @Modifying
    @Transactional
    int updateLevel(@Param(value = "level") Integer level, @Param(value = "uuid") String uuid);


    List<SysDomainAuthorization> findByDomainId(String domainId);

}
