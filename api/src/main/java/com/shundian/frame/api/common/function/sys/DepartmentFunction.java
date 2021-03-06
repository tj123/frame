package com.shundian.frame.api.common.function.sys;


import com.shundian.frame.api.common.BaseFunction;

/**
 * Created by TJ on 2016/7/15.
 */
public class DepartmentFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 2;
    }

    @Override
    public String getName() {
        return "部门管理";
    }

    @Override
    public String getUiSref() {
        return "app.sys.dep";
    }

    @Override
    public String getEntry() {
        return "sys/dep";
    }


}
