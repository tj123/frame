package com.shundian.frame.api.common.function.sys;


import com.shundian.frame.api.common.BaseFunction;

/**
 * Created by TJ on 2016/7/15.
 */
public class ConfigFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 5;
    }

    @Override
    public String getName() {
        return "参数配置管理";
    }

    @Override
    public String getUiSref() {
        return "app.sys.cfg";
    }

    @Override
    public String getEntry() {
        return "sys/cfg";
    }


}
