package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

public class Dep implements Unit {

    @Override
    public Integer getId() {
        return 3;
    }

    @Override
    public String getKey() {
        return "dep";
    }

    @Override
    public String getName() {
        return "部门管理";
    }
}
