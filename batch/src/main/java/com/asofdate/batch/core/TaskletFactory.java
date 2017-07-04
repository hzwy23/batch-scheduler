package com.asofdate.batch.core;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

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
        String cmd = Paths.get(basePath, scritpFile).toString();
        switch (typeId) {
            case CMD_TYPE:
                cmd = "cmd /c " + cmd;
                return new ExecTasklet(cmd);
            case SHELL_TYPE:
                cmd = "sh -x " + cmd;
                return new ExecTasklet(cmd);
            case JAR_TYPE:
                cmd = "java -jar " + cmd;
                return new ExecTasklet(cmd);
            case BINARY_TYPE:
                return new ExecTasklet(cmd);
            case PROC_TYPE:
                return new ProcTasklet(scritpFile, jdbcTemplate);
        }
        return null;
    }

}
