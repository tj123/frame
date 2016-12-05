package com.shundian.frame.common.function.sys;


import com.shundian.frame.common.BaseFunction;

/**
 * Created by TJ on 2016/7/15.
 */
public class RoleFunction extends BaseFunction {

    @Override
    public Short getId() {
        return 7;
    }

    @Override
    public String getName() {
        return "角色管理";
    }

    @Override
    public String getUiSref() {
        return "sys.role";
    }

}
