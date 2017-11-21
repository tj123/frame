package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

/**
 * Created by TJ on 2017/9/15.
 */
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
