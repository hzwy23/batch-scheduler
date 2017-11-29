package com.asofdate.batch.dao.impl.sql;

import com.asofdate.batch.dao.impl.sql.db.MySQLBatchSqlDefine;
import com.asofdate.batch.dao.impl.sql.db.OracleBatchSqlDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Component
public class DBType {
    private final Logger logger = LoggerFactory.getLogger(DBType.class);
    @Autowired
    private Environment environment;
    @Autowired
    private MySQLBatchSqlDefine mySQLBatchSqlDefine;
    @Autowired
    private OracleBatchSqlDefine oracleBatchSqlDefine;

    private String dbname;

    public SQLFactory getDbType() {
        if (dbname.equals("mysql")) {
            return mySQLBatchSqlDefine;
        } else if (dbname.equals("oracle")) {
            return oracleBatchSqlDefine;
        } else {
            logger.error("not support dbversion.{}", dbname);
            return null;
        }
    }

    @PostConstruct
    private void init() {
        this.dbname = environment.getProperty("spring.datasource.name").toLowerCase();
    }

}
