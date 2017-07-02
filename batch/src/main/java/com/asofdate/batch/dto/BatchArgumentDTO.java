package com.asofdate.batch.dto;

import java.io.Serializable;

/**
 * 批次参数类型信息，根据批次中配置的任务情况，动态的产生批次类型的参数
 * Created by hzwy23 on 2017/6/26.
 *
 * @author hzwy23
 */
public class BatchArgumentDTO implements Serializable {
    public String code_number;
    public String batch_id;
    public String arg_id;
    public String domain_id;
    public String bind_as_of_date;
    public String arg_desc;
    public String arg_value;

    public String getArgValue() {
        return arg_value;
    }

    public void setArgValue(String arg_value) {
        this.arg_value = arg_value;
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getBatchId() {
        return batch_id;
    }

    public void setBatchId(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getArgId() {
        return arg_id;
    }

    public void setArgId(String arg_id) {
        this.arg_id = arg_id;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getBindAsOfDate() {
        return bind_as_of_date;
    }

    public void setBindAsOfDate(String bind_as_of_date) {
        this.bind_as_of_date = bind_as_of_date;
    }

    public String getArgDesc() {
        return arg_desc;
    }

    public void setArgDesc(String arg_desc) {
        this.arg_desc = arg_desc;
    }


    @Override
    public String toString() {
        return "BatchArgumentDTO{" +
                "code_number='" + code_number + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", bind_as_of_date='" + bind_as_of_date + '\'' +
                ", arg_desc='" + arg_desc + '\'' +
                ", arg_value='" + arg_value + '\'' +
                '}';
    }
}
