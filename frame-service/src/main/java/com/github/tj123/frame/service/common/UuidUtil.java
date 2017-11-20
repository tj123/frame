package com.github.tj123.frame.service.common;

import java.util.UUID;

/**
 * Created by TJ on 2017/11/20.
 */
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
