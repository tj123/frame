package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SAreaPo;

import java.util.Map;

public interface SAreaService {

    void add(SAreaPo po) throws Exception;

    void del(String id) throws Exception;

    void edit(SAreaPo po) throws Exception;

    Map<String, Object> get2(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

}
