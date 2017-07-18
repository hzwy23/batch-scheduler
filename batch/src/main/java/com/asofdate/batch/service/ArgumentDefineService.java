package com.asofdate.batch.service;

import com.asofdate.batch.entity.ArgumentDefineEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/17.
 */
public interface ArgumentDefineService {
    /**
     * 查询指定域中的所有参数信息
     *
     * @param domainID 域编码信息
     * @return List 返回某一个域所有的参数定义信息
     */
    List<ArgumentDefineEntity> findAll(String domainID);

    /**
     * 向参数定义表中新增参数
     *
     * @param vo 参数信息
     */
    RetMsg addArgument(ArgumentDefineEntity vo);

    /**
     * 删除已经定义的参数信息
     *
     * @param list 准备删除的参数定义信息
     */
    RetMsg deleteArgument(List<ArgumentDefineEntity> list);

    /**
     * 更新参数信息
     *
     * @param vo
     */
    RetMsg updateArgument(ArgumentDefineEntity vo);
}
