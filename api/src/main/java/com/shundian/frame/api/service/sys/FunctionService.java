package com.shundian.frame.api.service.sys;

import com.shundian.frame.api.entity.sys.Function;
import com.shundian.frame.api.entity.sys.FunctionModule;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.function.FunctionType;
import com.shundian.lib.function.ModuleType;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface FunctionService {


    /**
     * 不存在就插入 存在则更新  uid 和  连接
     * @throws Exception
     */
    void insert(Map<Class<? extends FunctionType<?>>, List<Class<? extends ModuleType>>> functions) throws Exception;

    /**
     * 列表的 基础查询语句 可扩展
     * @param conditions
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> list(Map<String, String> conditions) throws Exception;

    /**
     * 列出所有功能
     * @return
     * @throws Exception
     */
    PageResult<Map<String,Object>> list(Page page) throws Exception;

    /**
     * 根据id列出
     * @return
     * @throws Exception
     */
    Map<String, Object> list(String id) throws Exception;

    List<FunctionModule> findModules() throws Exception;

    List<Function> findFunctions() throws Exception;

    /**
     * 列出所有的
     * @param name
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> listAll(String name) throws Exception;
    
}
