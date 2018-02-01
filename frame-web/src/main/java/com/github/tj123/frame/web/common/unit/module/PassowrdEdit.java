package com.github.tj123.frame.web.common.unit.module;

import com.github.tj123.common.auth.annotation.Unit;

public class PassowrdEdit implements Unit {

    @Override
    public Integer getId() {
        return 60;
    }

    @Override
    public String getKey() {
        return "pedt";
    }

    @Override
    public String getName() {
        return "密码修改";
    }
}
