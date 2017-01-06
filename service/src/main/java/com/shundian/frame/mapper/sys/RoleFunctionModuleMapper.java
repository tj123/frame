package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.RoleFunctionModulePo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleFunctionModuleMapper extends Mapper<RoleFunctionModulePo> {

    void insertMulti(List<RoleFunctionModulePo> functionModules) throws Exception;

}

