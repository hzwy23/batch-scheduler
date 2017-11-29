package com.asofdate.hauth.sql;

import com.asofdate.hauth.sql.db.MySqlDefine;
import com.asofdate.hauth.sql.db.OracleDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * Created by hzwy23 on 2017/7/21.
 */
@Configuration
public class DbType {
    private final Logger logger = LoggerFactory.getLogger(DbType.class);

    @Autowired
    private Environment environment;
    @Autowired
    private MySqlDefine mySqlDefine;
    @Autowired
    private OracleDefine oracleDefine;

    private String dbname;

    public SQLFactory getDbType() {
        if (dbname.equals("mysql")) {
            return mySqlDefine;
        } else if (dbname.equals("oracle")) {
            return oracleDefine;
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
