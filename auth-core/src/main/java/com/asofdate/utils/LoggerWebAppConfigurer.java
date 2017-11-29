package com.asofdate.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hzwy23 on 2017/6/19.
 */
@Configuration
public class LoggerWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private LoggerHandlerInterceptor loggerHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerHandlerInterceptor).addPathPatterns("/v1/**");
        super.addInterceptors(registry);
    }

}
