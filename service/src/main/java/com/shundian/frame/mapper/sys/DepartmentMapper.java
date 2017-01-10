package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.DepartmentPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper extends Mapper<DepartmentPo> {

    List<Map<String,Object>> list(Map<String, Object> keywords) throws Exception;


}

