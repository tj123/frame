package com.github.tj123.frame.api.common.utils;

import java.util.UUID;

public class UuidUtils {

    /**
     * uuid 统一生成
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

}
