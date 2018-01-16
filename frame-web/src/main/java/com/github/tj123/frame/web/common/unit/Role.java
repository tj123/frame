package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

public class Role implements Unit {

    @Override
    public Integer getId() {
        return 2;
    }

    @Override
    public String getKey() {
        return "rol";
    }

    @Override
    public String getName() {
        return "角色管理";
    }
}
