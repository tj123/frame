package com.github.tj123.frame.web.common.unit.module;

import com.github.tj123.common.auth.annotation.Unit;

public class View implements Unit {

    @Override
    public Integer getId() {
        return 7;
    }

    @Override
    public String getKey() {
        return "vew";
    }

    @Override
    public String getName() {
        return "查看";
    }
}