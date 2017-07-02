package com.asofdate.utils.factory;

import com.asofdate.hauth.dto.AuthDTO;

/**
 * Created by hzwy23 on 2017/6/29.
 */
public class AuthDTOFactory {
    public static AuthDTO getAutoDTO(Boolean status, String message) {
        return new AuthDTO(status, message);
    }
}
