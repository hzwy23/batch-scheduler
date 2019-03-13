package com.asofdate.batch.service.feign;

import com.asofdate.batch.utils.ResultBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "task-executor")
public interface TaskExecutorFeign {

    @RequestMapping(value = "/batch/schedule/v1/dispatch/start",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResultBody start(@RequestParam(value = "batchId") String batchId,
                     @RequestParam(value = "domainId") String domainId);
}
