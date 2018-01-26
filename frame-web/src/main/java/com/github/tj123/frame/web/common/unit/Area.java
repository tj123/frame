package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

public class Area implements Unit {

    @Override
    public Integer getId() {
        return 5;
    }

    @Override
    public String getKey() {
        return "ara";
    }

    @Override
    public String getName() {
        return "区域管理";
    }
}
