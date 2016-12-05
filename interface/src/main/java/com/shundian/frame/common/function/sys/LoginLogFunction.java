package com.shundian.frame.common.function.sys;


import com.shundian.frame.common.BaseFunction;

/**
 * Created by TJ on 2016/7/15.
 */
public class LoginLogFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 4;
    }

    @Override
    public String getName() {
        return "登录日志管理";
    }

    @Override
    public String getUiSref() {
        return "sys.log";
    }

}
