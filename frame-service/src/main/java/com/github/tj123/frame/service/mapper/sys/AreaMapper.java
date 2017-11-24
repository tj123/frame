package com.github.tj123.frame.service.mapper.sys;

import com.github.tj123.frame.api.po.sys.DepPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/10/12.
 */
@Mapper
public interface AreaMapper extends BaseMapper<DepPo>{


    @Select("select * from s_dep")
    List<Map<String,Object>> list();

    @Select("SELECT area_id areaId,parent_id parentId,name FROM `s_area` " +
            "where INSTR(area_id,#{areaId}) = 1")
    List<Map<String,Object>> areas(String areaId);
}
