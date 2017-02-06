package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.DepartmentPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper extends Mapper<DepartmentPo> {

    List<Map<String,Object>> list(Map<String, Object> keywords) throws Exception;


    List<Map<String,Object>> search(@Param("name") String name,@Param("limit") int limit) throws Exception;
}

