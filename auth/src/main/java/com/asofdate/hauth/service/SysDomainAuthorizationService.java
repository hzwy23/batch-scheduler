package com.asofdate.hauth.service;

import com.asofdate.hauth.entity.DomainEntity;
import com.asofdate.hauth.vo.SysDomainAuthorizationAddParamVo;
import com.asofdate.hauth.vo.SysDomainAuthorizationVo;
import com.asofdate.utils.RetMsg;

import java.util.List;

public interface SysDomainAuthorizationService {

    /**
     * 查询用户能够访问到的项目
     */
    List<SysDomainAuthorizationVo> findAll(String userId);

    /**
     * 设置成默认访问的项目
     *
     * @param uuid
     */
    RetMsg modifyDefaultDomain(String uuid, String userId);


    /**
     * 查询未授权的用户
     * @param domainId
     * */
    List<SysDomainAuthorizationVo> findUnauahtUserByDomainId(String domainId);


    /**
     * 根据项目ID，查询这个项目权限授予了哪些用户
     * @param domainId
     * */
    List<SysDomainAuthorizationVo> findByDomainId(String domainId);

    /**
     * 添加授权
     */
    RetMsg grant(List<SysDomainAuthorizationAddParamVo> paramVo, String handleUserId);


    /**
     * 移除权限
     *
     * @param uuid
     */
    RetMsg revoke(String uuid);


    /**
     * 批量删除授权信息
     * @param uuids
     * */
    RetMsg batchRevoke(List<String> uuids);


    /**
     * 修改授权模式
     *
     * @param level
     * @param uuid
     */
    RetMsg modify(String uuid, int level);


    /**
     * 查询用户没有获取到的项目
     *
     * @param userId
     * @return List<SysDomainAuthorizationVo>
     */
    List<DomainEntity> findUnauth(String userId);

}
