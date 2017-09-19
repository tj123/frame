package com.github.tj123.frame.web.common.unit;

import com.github.tj123.common.auth.annotation.Unit;

/**
 * Created by TJ on 2017/9/15.
 */
public class User implements Unit {

    @Override
    public Integer getId() {
        return 1;
    }

    @Override
    public String getKey() {
        return "usr";
    }

    @Override
    public String getName() {
        return "用户管理";
    }
}
