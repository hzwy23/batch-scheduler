package com.asofdate.batch.core;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hzwy23 on 2017/5/31.
 */
@Component
@Scope("prototype")
public class TaskletFactory {

    // shell 脚本
    private final String SHELL_TYPE = "1";
    // 存储过程
    private final String PROC_TYPE = "2";
    // cmd 脚本
    private final String CMD_TYPE = "3";
    // jar包
    private final String JAR_TYPE = "4";
    // 二进制
    private final String BINARY_TYPE = "5";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Tasklet getTasklet(String typeId, String scritpFile, String basePath) {
        switch (typeId) {
            case CMD_TYPE:
                return new CmdTasklet(scritpFile, basePath);
            case SHELL_TYPE:
                return new ShellTasklet(scritpFile, basePath);
            case JAR_TYPE:
                return new JarTasklet(scritpFile, basePath);
            case BINARY_TYPE:
                return new BinaryTasklet(scritpFile, basePath);
            case PROC_TYPE:
                return new ProcTasklet(scritpFile, jdbcTemplate);
        }
        return null;
    }

}
