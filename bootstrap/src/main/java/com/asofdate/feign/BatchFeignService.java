package com.asofdate.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "batch-task-executor")
public interface BatchFeignService {

    @RequestMapping(value = "/v1/dispatch/argument/define", method = RequestMethod.GET)
    Map findArgs();

}
