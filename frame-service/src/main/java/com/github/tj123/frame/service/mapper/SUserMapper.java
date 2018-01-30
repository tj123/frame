package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.pojo.po.SUserPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SUserMapper extends BaseMapper<SUserPo> {

    Map<String, Object> get(String id);

    List<Map<String, Object>> list(PageRequest request);

    Map<String,Object> selectUser(String userName);

    List<Map<String,Object>> auth(String userId);

    Integer isExistUsername(String username);
}
