package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SUserPo;

import java.util.Map;

public interface SUserService {

    void add(SUserPo po) throws Exception;

    void del(String id) throws Exception;

    void edit(SUserPo po) throws Exception;

    SUserPo get(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

}
