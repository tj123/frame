package com.shundian.frame.mapper.sys;


import com.shundian.frame.model.sys.Function;
import tk.mybatis.mapper.common.Mapper;

public interface FunctionMapper extends Mapper<Function> {

    Integer selectDuplicate(Function function) throws Exception;

    void updateFunction(Function function) throws Exception;

}
