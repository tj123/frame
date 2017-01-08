package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.RoleFunctionModulePo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleFunctionModuleMapper extends Mapper<RoleFunctionModulePo> {
  
  void insertMulti(List<RoleFunctionModulePo> functionModules) throws Exception;
  
  void removeMulti(@Param("role") String role,@Param("funcs") String[] funcs) throws Exception;
}

