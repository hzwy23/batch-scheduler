package com.asofdate.hauth.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Configuration
public class SqlText {
    private final Logger logger = LoggerFactory.getLogger(SqlText.class);
    private final String SQLNOTFOUND = "";
    @Autowired
    private DbType dbType;

    public String getSql(String id) {
        SQLFactory sql = dbType.getDbType();
        try {
            return sql.getSqlText(id);
        } catch (NoSuchFieldException e) {
            logger.info("获取SQL信息失败，失败原因是：", e.getMessage());
        } catch (IllegalAccessException e) {
            logger.info("获取SQL信息失败，失败原因是：", e.getMessage());
        }
        return SQLNOTFOUND;
    }
}
