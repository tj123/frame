package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.pojo.po.SUserRolePo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SUserRoleMapper extends BaseMapper<SUserRolePo> {

    Map<String, Object> get(String id);

    List<Map<String, Object>> list(PageRequest request);

    List<String> getUserRole(String id);
}
