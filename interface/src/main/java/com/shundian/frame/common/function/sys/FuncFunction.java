package com.shundian.frame.common.function.sys;


import com.shundian.frame.common.BaseFunction;


public class FuncFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 1;
    }

    @Override
    public String getName() {
        return "功能管理";
    }

    @Override
    public String getUiSref() {
        return "app.sys.func";
    }

    @Override
    public String getEntry() {
        return "sys/func";
    }

}
