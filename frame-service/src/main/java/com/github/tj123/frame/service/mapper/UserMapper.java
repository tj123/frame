package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.UserPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by TJ on 2017/10/12.
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPo>{

    Map<String,Object> selectUser(String userName);


}
