package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.sys.RolePo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/10/12.
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePo>{

    @Select("SELECT r.id,r.name,r.description FROM s_role r")
    List<Map<String,Object>> list();
}
