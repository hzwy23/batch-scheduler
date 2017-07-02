package com.asofdate.utils;

/**
 * Created by hzwy23 on 2017/6/30.
 */
public class Validator {
    public static Boolean isAdminDomain(String domainID) {
        if ("vertex_root".equals(domainID)) {
            return true;
        }
        return false;
    }
}
