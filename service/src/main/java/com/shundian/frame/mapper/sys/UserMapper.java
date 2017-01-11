package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.UserPo;
import com.shundian.lib.function.MiniFunction;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<UserPo> {

    List<Map<String,Object>> list(Map<String, Object> stringObjectMap) throws Exception;

    Map<String,Object> selectUserId(String username) throws Exception;

    List<MiniFunction> getFunctions(String userId) throws Exception;
}

