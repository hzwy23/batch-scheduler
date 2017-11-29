package com.asofdate.utils;

/**
 * Created by hzwy23 on 2017/5/31.
 */
public class JoinCode {
    private final static String SPLIT_STRING = "__hzwy23__";

    public static String join(String str1, String str2) {
        return str1 + SPLIT_STRING + str2;
    }

    public static String getTaskCode(String str) {
        String[] tmp = str.split(SPLIT_STRING);
        if (tmp.length == 2) {
            return tmp[1];
        } else {
            return str;
        }
    }

    public static String getGroupCode(String str) {
        String[] tmp = str.split(SPLIT_STRING);
        if (tmp.length == 2) {
            return tmp[0];
        } else {
            return str;
        }
    }

    public static String getFirst(String str) {
        String[] tmp = str.split(SPLIT_STRING);
        if (tmp.length == 2) {
            return tmp[0];
        } else {
            return str;
        }
    }

    public static String getLast(String str) {
        String[] tmp = str.split(SPLIT_STRING);
        if (tmp.length == 2) {
            return tmp[1];
        } else {
            return str;
        }
    }
}
