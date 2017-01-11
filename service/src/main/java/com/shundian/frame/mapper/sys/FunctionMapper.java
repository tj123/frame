package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.FunctionPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FunctionMapper extends Mapper<FunctionPo> {

    Integer selectDuplicate(FunctionPo functionPo) throws Exception;

    void updateFunction(FunctionPo functionPo) throws Exception;

    List<Map<String,Object>> list(Map<String, String> keywords) throws Exception;


}

