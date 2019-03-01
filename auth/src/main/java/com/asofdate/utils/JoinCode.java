package com.asofdate.utils;

import com.google.common.io.BaseEncoding;

/**
 * Created by hzwy23 on 2017/5/31.
 */
public class JoinCode {

    private final static Character SEPARATOR = 0x1E;

    private final static String SPLIT_STRING = SEPARATOR + "wisrc" + SEPARATOR ;

    public static String join(String str1, String str2) {
        return BaseEncoding.base64().encode(str1.concat(SPLIT_STRING).concat(str2).getBytes());
    }

    public static String getFirst(String baseStr) {
        String str = new String(BaseEncoding.base64().decode(baseStr));
        String[] tmp = str.split(SPLIT_STRING);
        if (tmp.length == 2) {
            return tmp[0];
        } else {
            return str;
        }
    }

    public static String getLast(String baseStr) {
        String str = new String(BaseEncoding.base64().decode(baseStr));
        String[] tmp = str.split(SPLIT_STRING);
        if (tmp.length == 2) {
            return tmp[1];
        } else {
            return str;
        }
    }
}
