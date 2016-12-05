package com.shundian.frame.api.sys;

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
}