package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SDepPo;

import java.util.Map;

public interface SDepService {

    void add(SDepPo po) throws Exception;

    void del(String id) throws Exception;

    void edit(SDepPo po) throws Exception;

    SDepPo get(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

}