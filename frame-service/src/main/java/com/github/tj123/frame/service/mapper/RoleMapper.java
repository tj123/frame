package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.sys.RolePo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface RoleMapper extends BaseMapper<RolePo>{

    List<Map<String,Object>> list();

    List<Map<String,Object>> listAll();

    Map<String,Object> get(String id);

    List<Map<String,Object>> listDep(String depId);
}
