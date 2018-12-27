package com.asofdate.batch.core;

public enum  TaskTypeEnum {
    SHELL_TYPE(1,"Shell 脚本"),
    PROC_TYPE(2, "存储过程"),
    CMD_TYPE(3, "Window脚本"),
    JAR_TYPE(4,"Java Jar包"),
    BINARY_TYPE(5, "可执行文件");

    private final int code;
    private final String desc;
    TaskTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static TaskTypeEnum getTaskTypeEnum(int code) {
        switch (code){
            case 1:
                return TaskTypeEnum.SHELL_TYPE;
            case 2:
                return TaskTypeEnum.PROC_TYPE;
            case 3:
                return TaskTypeEnum.CMD_TYPE;
            case 4:
                return TaskTypeEnum.JAR_TYPE;
            case 5:
                return TaskTypeEnum.BINARY_TYPE;
            default:
                return TaskTypeEnum.BINARY_TYPE;
        }
    }

}
