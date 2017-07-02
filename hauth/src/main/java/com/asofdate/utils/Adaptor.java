package com.asofdate.utils;

import com.asofdate.sql.OracleDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by hzwy23 on 2017/6/8.
 */
@Component
public class Adaptor {
    private static Adaptor adaptor;
    @Autowired
    private Environment env;

    public static void initDb() {
        String dbname = adaptor.env.getProperty("spring.jpa.database").toLowerCase();
        switch (dbname) {
            case "oracle":
                OracleDefine.oracleInit();
                break;
        }
    }

    @PostConstruct
    public void init() {
        adaptor = this;
        adaptor.env = this.env;
    }
}
