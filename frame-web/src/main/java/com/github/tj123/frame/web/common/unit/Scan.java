package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

public class Scan implements Unit {

    @Override
    public Integer getId() {
        return 8;
    }

    @Override
    public String getKey() {
        return "scan";
    }

    @Override
    public String getName() {
        return "扫描";
    }
}
