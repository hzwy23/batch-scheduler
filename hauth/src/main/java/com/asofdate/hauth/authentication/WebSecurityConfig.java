package com.asofdate.hauth.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

/**
 * Created by hzwy23 on 2017/5/18.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomAuthenticationProvider customAuthenticationProvider;

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                .antMatchers("/bootstrap-3.3.7-dist/**", "/bootstrap-switch-master/**").permitAll()
                .antMatchers("/bootstrap-table/**", "/Font-Awesome-3.2.1/**", "/favicon.ico").permitAll()
                .antMatchers("/images/**", "/css/**", "/js/**", "/laydate/**", "/nprogress/**").permitAll()
                .antMatchers("/swagger/**", "/theme/**", "/webuploader/**", "/jquery-i18n-properties/**").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 对login进行过滤
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 对其他的api进行过滤
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        http.requestCache().requestCache(new NullRequestCache());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
