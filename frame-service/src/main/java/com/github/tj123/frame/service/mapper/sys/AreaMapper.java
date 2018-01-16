package com.github.tj123.frame.service.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AreaMapper {

    @Select("SELECT area_id areaId,parent_id parentId,name FROM `s_area` " +
            "where INSTR(area_id,#{areaId}) = 1")
    List<Map<String, Object>> areas(String areaId);
}
