package com.shundian.frame.mapper.sys;

import com.shundian.frame.model.sys.FunctionModule;
import tk.mybatis.mapper.common.Mapper;

public interface FunctionModuleMapper extends Mapper<FunctionModule> {

    void insertUpdate(FunctionModule functionModule) throws Exception;

}
