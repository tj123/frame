package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

public class User implements Unit {

    @Override
    public Integer getId() {
        return 1;
    }

    @Override
    public String getKey() {
        return "usr";
    }

    @Override
    public String getName() {
        return "用户管理";
    }
}
