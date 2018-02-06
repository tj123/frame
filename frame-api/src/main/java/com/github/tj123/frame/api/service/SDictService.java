package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SDictItemPo;
import com.github.tj123.frame.api.pojo.po.SDictPo;

import java.util.List;
import java.util.Map;

public interface SDictService {

    void add(SDictPo po, List<SDictItemPo> items) throws Exception;

    void del(String id) throws Exception;

    void edit(SDictPo po, List<SDictItemPo> items) throws Exception;

    Map<String, Object> get2(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

    /**
     * @param type         "code","enum" 默认枚举
     * @param code         字典的编码 或者是 枚举值
     * @param depId 部门
     * @return 没有递归处理
     * @throws Exception
     */
    List<Map<String, Object>> getDict(String type, String code, String depId) throws Exception;

}
