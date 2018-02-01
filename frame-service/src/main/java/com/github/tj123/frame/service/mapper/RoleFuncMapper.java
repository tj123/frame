package com.github.tj123.frame.service.mapper;

import com.github.tj123.frame.api.po.sys.RoleFuncPo;
import com.github.tj123.frame.service.common.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleFuncMapper extends BaseMapper<RoleFuncPo>{

    List<String> getRoleFunc(String id);

}
