package com.shundian.frame.mapper.sys;

import com.shundian.frame.api.po.sys.FunctionModule;
import tk.mybatis.mapper.common.Mapper;

public interface FunctionModuleMapper extends Mapper<FunctionModule> {

    Integer selectDuplicate(FunctionModule module) throws Exception;

    void updateModule(FunctionModule functionModule) throws Exception;
}
