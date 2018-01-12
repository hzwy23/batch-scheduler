package com.asofdate.hauth.service;

import com.asofdate.hauth.dto.AuthDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hzwy23 on 2017/6/19.
 */
public interface AuthService {
    AuthDto domainAuth(HttpServletRequest request, String domainId, String mode);

    AuthDto basicAuth(HttpServletRequest request);
}
