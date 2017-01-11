package com.shundian.frame.common.function.sys;


import com.shundian.frame.common.BaseFunction;

/**
 * Created by TJ on 2016/7/15.
 */
public class UserFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 3;
    }

    @Override
    public String getName() {
        return "用户管理";
    }

    @Override
    public String getUiSref() {
        return "app.sys.user";
    }

    @Override
    public String getEntry() {
        return "sys/user";
    }

}
