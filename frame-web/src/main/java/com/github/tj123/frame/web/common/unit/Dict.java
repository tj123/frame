package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

public class Dict implements Unit {

    @Override
    public Integer getId() {
        return 7;
    }

    @Override
    public String getKey() {
        return "dict";
    }

    @Override
    public String getName() {
        return "数据字典";
    }
}
