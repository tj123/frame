package com.github.tj123.frame.web.common.unit.module;

import com.github.tj123.common.auth.annotation.Unit;

public class Del implements Unit {

    @Override
    public Integer getId() {
        return 5;
    }

    @Override
    public String getKey() {
        return "del";
    }

    @Override
    public String getName() {
        return "删除";
    }
}
