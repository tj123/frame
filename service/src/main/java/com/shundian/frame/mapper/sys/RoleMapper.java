package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.entity.sys.SearchFunction;
import com.shundian.frame.api.po.sys.RolePo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<RolePo> {

    List<Map<String,Object>> list(Map<String, String> keywords) throws Exception;


}

