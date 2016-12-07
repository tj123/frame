package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.Function;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FunctionMapper extends Mapper<Function> {

    Integer selectDuplicate(Function function) throws Exception;

    void updateFunction(Function function) throws Exception;

    List<Map<String,Object>> list(Map<String, String> keywords) throws Exception;

}

