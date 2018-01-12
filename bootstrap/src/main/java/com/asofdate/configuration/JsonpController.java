package com.asofdate.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Created by hzwy23 on 2017/7/24.
 */
@ControllerAdvice(basePackages = {
        "com.asofdate.batch.controller",
        "com.asofdate.hauth.controller"
})
public class JsonpController extends AbstractJsonpResponseBodyAdvice {
    public JsonpController() {
        super("callback", "jsonp");
    }
}
