package com.asofdate.hauth.authentication;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by hzwy23 on 2017/5/18.
 */

public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
