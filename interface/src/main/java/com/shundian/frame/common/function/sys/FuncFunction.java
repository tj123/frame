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
        return "sys.func";
    }

}
