package com.shundian.frame.mapper.sys;

import com.shundian.frame.api.entity.sys.FunctionModule;
import com.shundian.frame.api.entity.sys.SearchFunction;
import com.shundian.frame.api.po.sys.FunctionModulePo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FunctionModuleMapper extends Mapper<FunctionModulePo> {
  
  Integer selectDuplicate(FunctionModulePo module) throws Exception;
  
  void updateModule(FunctionModulePo functionModulePo) throws Exception;
  
  List<FunctionModule> selectFunctionModule(FunctionModulePo functionModulePo) throws Exception;
  
  /**
   * 列出所有的功能
   *
   * @param name
   * @param userId
   * @param project
   * @return
   * @throws Exception
   */
  List<SearchFunction> listAll(@Param("name") String name, @Param("userId") String userId, @Param("project") String project) throws Exception;
  
  /**
   *  列出角色所具有的所有功能模块
   *
   * @param role
   * @param name
   * @return
   * @throws Exception
   */
  List<String> listModules(@Param("role") String role,@Param("name") String name) throws Exception;
}
