package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

/**
 * Created by TJ on 2017/9/15.
 */
public class Edit implements Unit {

    @Override
    public Integer getId() {
        return 6;
    }

    @Override
    public String getKey() {
        return "edt";
    }

    @Override
    public String getName() {
        return "修改";
    }
}
