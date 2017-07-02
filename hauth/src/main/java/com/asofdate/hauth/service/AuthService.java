package com.asofdate.hauth.service;

import com.asofdate.hauth.dto.AuthDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hzwy23 on 2017/6/19.
 */
public interface AuthService {
    AuthDTO domainAuth(HttpServletRequest request, String domainId, String mode);

    AuthDTO basicAuth(HttpServletRequest request);
}
