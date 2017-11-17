package com.github.tj123.frame.service.mapper;

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
public interface DepMapper extends BaseMapper<DepPo>{


    @Select("select * from s_dep")
    List<Map<String,Object>> list();

}
