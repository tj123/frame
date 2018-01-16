package com.github.tj123.frame.service.common;

import java.util.UUID;

public class UuidUtil {

    /**
     * uuid 统一生成
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

}
