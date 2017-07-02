package com.asofdate.batch.utils;

/**
 * Created by hzwy23 on 2017/6/2.
 */
public class BatchStatus {
    //    BATCH_STATUS_INVALID,
    public static final int BATCH_STATUS_INIT = 0;
    public static final int BATCH_STATUS_RUNNING = 1;
    public static final int BATCH_STATUS_COMPLETED = 2;
    public static final int BATCH_STATUS_ERROR = 3;
    public static final int BATCH_STATUS_STOPPED = 4;
}
