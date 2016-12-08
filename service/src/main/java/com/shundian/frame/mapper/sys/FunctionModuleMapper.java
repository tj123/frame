package com.shundian.frame.mapper.sys;

import com.shundian.frame.api.entity.sys.FunctionModule;
import com.shundian.frame.api.po.sys.FunctionModulePo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FunctionModuleMapper extends Mapper<FunctionModulePo> {

    Integer selectDuplicate(FunctionModulePo module) throws Exception;

    void updateModule(FunctionModulePo functionModulePo) throws Exception;

    List<FunctionModule> selectFunctionModule(FunctionModulePo functionModulePo) throws Exception;
}
