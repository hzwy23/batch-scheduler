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
     * 添加授权
     */
    RetMsg grant(SysDomainAuthorizationAddParamVo paramVo, String handleUserId);


    /**
     * 移除权限
     *
     * @param uuid
     */
    RetMsg revoke(String uuid);


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
