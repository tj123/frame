package com.shundian.frame.api.common.function.sys;


import com.shundian.frame.api.common.BaseFunction;


public class SystemManageFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 8;
    }

    @Override
    public String getName() {
        return "系统管理";
    }

    @Override
    public String getUiSref() {
        return "sys";
    }

    @Override
    public String getEntry() {
        return "sys";
    }

}
