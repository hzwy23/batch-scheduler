package com.asofdate.batch.service;

import com.asofdate.batch.entity.ArgumentDefineEntity;
import com.asofdate.batch.entity.TaskArgumentEntity;
import com.asofdate.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/28.
 *
 * @author hzwy23
 */
public interface ArgumentService {
    /**
     * 初始化参数信息
     * 在使用@Autowired自动注入这个类得实例后
     * 需要手工调用这个方法初始化对象中的变量属性
     * ArgumentServiceImpl中定义的属性，需要调用这个方法初始化
     *
     * @param domainId
     * @param batchId
     */
    void afterPropertySet(String domainId, String batchId);

    /**
     * 参数 id 是 dispatch_group_task_relation表中的id字段
     * 根据id值,查询这个任务所有的参数信息
     * 只有执行了上边的afterPropertySet函数, 初始化对象的属性后,
     * queryArgument方法才会返回正确的值,否则为null
     *
     * @param id 任务组中任务的key
     */
    List<TaskArgumentEntity> queryArgument(String id);

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
