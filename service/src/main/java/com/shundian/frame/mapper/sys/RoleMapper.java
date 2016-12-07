package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<Role> {

    List<Map<String,Object>> list(Map<String, String> keywords) throws Exception;

}

