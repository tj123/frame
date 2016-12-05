package com.shundian.frame.common.function.sys;


import com.shundian.frame.common.BaseFunction;
import com.shundian.frame.envm.ProjectTypeEnum;
import com.shundian.lib.function.FunctionType;

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
        return "sys.cfg";
    }


}
