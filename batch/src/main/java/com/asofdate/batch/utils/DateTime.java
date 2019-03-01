package com.asofdate.batch.utils;

import java.text.SimpleDateFormat;

public class DateTime {
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }
}
