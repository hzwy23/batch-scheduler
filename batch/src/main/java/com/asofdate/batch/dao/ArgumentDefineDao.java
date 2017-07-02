package com.asofdate.batch.dao;

import com.asofdate.batch.entity.ArgumentDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
public interface ArgumentDefineDao {

    /**
     * 查询某一个域中所有的参数
     *
     * @param domainId 所属域编码
     *                 返回参数定义表中,所以参数,如果参数类型是固定参数,则返回固定参数的值
     */
    List findAll(String domainId);

    /**
     * 新增参数
     *
     * @param vo 新增参数信息
     * @return 返回插入的行数
     */
    int add(ArgumentDefineEntity vo);

    /**
     * 删除参数信息
     *
     * @param vo 需要删除的参数列表
     * @return 返回被删除的行数
     */
    String delete(List<ArgumentDefineEntity> vo);

    /**
     * 更新参数
     *
     * @param vo 需要被更新的对象
     * @return 返回更新的行数
     */
    int update(ArgumentDefineEntity vo);
}
