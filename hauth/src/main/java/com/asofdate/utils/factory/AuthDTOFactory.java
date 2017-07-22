package com.asofdate.utils.factory;

import com.asofdate.hauth.dto.AuthDto;

/**
 * Created by hzwy23 on 2017/6/29.
 */
public class AuthDTOFactory {
    public static AuthDto getAuthDTO(Boolean status, String message) {
        return new AuthDto(status, message);
    }
}
