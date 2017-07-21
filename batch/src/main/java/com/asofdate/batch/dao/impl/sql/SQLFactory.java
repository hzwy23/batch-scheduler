package com.asofdate.batch.dao.impl.sql;

/**
 * Created by hzwy23 on 2017/7/21.
 */
public interface SQLFactory {
    String getSqlText(String id) throws NoSuchFieldException, IllegalAccessException;
}
