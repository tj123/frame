package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

/**
 * Created by TJ on 2017/9/15.
 */
public class Func implements Unit {

    @Override
    public Integer getId() {
        return 30;
    }

    @Override
    public String getKey() {
        return "func";
    }

    @Override
    public String getName() {
        return "哈哈管理";
    }
}
