package com.asofdate.batch.utils;

import lombok.Data;

@Data
public class ResultBody {
    private Integer code;
    private String message;
    private Object data;

    public ResultBody(){}


    public ResultBody(Integer code, String message, Object data){
        this.code = code;
        this.message  = message;
        this.data = data;
    }
}
