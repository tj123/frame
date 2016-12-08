package com.shundian.frame.api.entity.sys;

import com.shundian.frame.api.po.sys.FunctionModulePo;
import com.shundian.frame.api.po.sys.FunctionPo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 功能对象
 */
@Setter
@Getter
public class Function extends FunctionPo {

    private List<FunctionModulePo> modules;

}
