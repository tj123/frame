package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SDictPo;

import java.util.Map;

public interface SDictService {

    void add(SDictPo po) throws Exception;

    void del(String id) throws Exception;

    void edit(SDictPo po) throws Exception;

    Map<String, Object> get2(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

}
