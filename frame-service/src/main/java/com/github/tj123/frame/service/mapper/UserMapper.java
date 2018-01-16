package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.UserPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper extends BaseMapper<UserPo>{

    Map<String,Object> selectUser(String userName);


    List<Map<String,Object>> auth(String userId);
}
