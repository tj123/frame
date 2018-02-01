package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SDepPo;

import java.util.List;
import java.util.Map;

public interface SDepService {

    void add(SDepPo po, List<String> roles) throws Exception;

    void del(String id) throws Exception;

    void edit(SDepPo po, List<String> roles) throws Exception;

    Map<String,Object> get(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

    List<Map<String, Object>> areas(String areaId) throws Exception;

    List<Map<String,Object>> search(String name) throws Exception;
}
