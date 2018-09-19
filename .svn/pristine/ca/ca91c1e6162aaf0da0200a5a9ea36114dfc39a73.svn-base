package com.dee.xql.api.utils;

import java.util.UUID;

/**
 * Created: Dee
 * Date   : 2016/11/30.
 */
public class UUIDLong {

    public static long longUUID() {
        return UUID.randomUUID().getMostSignificantBits();
    }

    public static long absLongUUID() {
        long r;
        do r = longUUID();
        while (r <= 0L);
        return r;
    }
}
