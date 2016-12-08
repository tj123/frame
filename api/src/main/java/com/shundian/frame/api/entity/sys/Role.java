package com.shundian.frame.api.entity.sys;

import com.shundian.frame.api.po.sys.FunctionModulePo;
import com.shundian.frame.api.po.sys.RolePo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色对象
 */
@Setter
@Getter
public class Role extends RolePo {

    private List<FunctionModulePo> modules;

}
