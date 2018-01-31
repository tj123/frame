package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.pojo.po.SAreaPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SAreaMapper extends BaseMapper<SAreaPo> {

    Map<String, Object> get(String id);

    List<Map<String, Object>> list(PageRequest request);

    @Select("SELECT area_id areaId,parent_id parentId,name FROM `s_area` " +
            "where INSTR(area_id,#{areaId}) = 1")
    List<Map<String, Object>> areas(String areaId);

}
