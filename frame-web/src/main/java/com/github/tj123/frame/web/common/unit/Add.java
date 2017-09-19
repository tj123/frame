package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

/**
 * Created by TJ on 2017/9/15.
 */
public class Add implements Unit {

    @Override
    public Integer getId() {
        return 4;
    }

    @Override
    public String getKey() {
        return "add";
    }

    @Override
    public String getName() {
        return "添加";
    }
}
