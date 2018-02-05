package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.sys.FunctionPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface FuncMapper extends BaseMapper<FunctionPo>{


    List<Map<String,Object>> list(Map<String,Object> map);

    List<Map<String,Object>> listAll();

    List<Map<String,Object>> allAuth();
}
