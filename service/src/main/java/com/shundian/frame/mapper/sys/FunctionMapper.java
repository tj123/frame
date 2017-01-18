package com.shundian.frame.mapper.sys;


import com.shundian.frame.api.po.sys.FunctionPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface FunctionMapper extends Mapper<FunctionPo> {

    /**
     *  查询重复的
     *
     * @param functionPo
     * @return
     * @throws Exception
     */
    Integer selectDuplicate(FunctionPo functionPo) throws Exception;

    /**
     * 更新
     *
     * @param functionPo
     * @throws Exception
     */
    void updateFunction(FunctionPo functionPo) throws Exception;

    /**
     * 列出
     *
     * @param keywords
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> list(Map<String, String> keywords) throws Exception;


}

