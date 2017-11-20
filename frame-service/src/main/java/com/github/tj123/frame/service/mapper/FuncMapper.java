package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.sys.FunctionPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/10/12.
 */
@Mapper
public interface FuncMapper extends BaseMapper<FunctionPo>{


    @Select("select id,uid,parent_uid parentId,key_ \"key\",name,full_name fullName from s_func")
    List<Map<String,Object>> list();

}
