package com.shundian.frame.api.sys;

import com.shundian.lib.function.FunctionType;
import com.shundian.lib.function.ModuleType;

/**
 *
 */
public interface FunctionService {


    /**
     * 不存在就插入 存在则更新  uid 和  连接
     * @param functionClass
     * @param moduleClass
     * @param <F>
     * @param <M>
     * @throws Exception
     */
    <F extends FunctionType<?>, M extends ModuleType> void insertUpdateModule(Class<F> functionClass, Class<M> moduleClass) throws Exception;


}
