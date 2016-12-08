package com.shundian.frame.api.entity.sys;

import com.shundian.frame.api.po.sys.FunctionModulePo;
import com.shundian.frame.api.po.sys.FunctionPo;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能模块对象
 */
@Setter
@Getter
public class FunctionModule  extends FunctionModulePo{

    private FunctionPo function;


}
