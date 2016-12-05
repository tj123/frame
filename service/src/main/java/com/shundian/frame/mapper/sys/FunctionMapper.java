package com.shundian.frame.mapper.sys;


import com.shundian.frame.model.sys.Function;
import tk.mybatis.mapper.common.Mapper;

public interface FunctionMapper extends Mapper<Function> {

    void insertUpdate(Function function) throws Exception;

}

