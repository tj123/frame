package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SUserPo;

import java.util.List;
import java.util.Map;

public interface SUserService {

    void add(SUserPo po) throws Exception;

    void del(String id) throws Exception;

    void edit(SUserPo po) throws Exception;

    Map<String, Object> get2(String id) throws Exception;

    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

    String login(String userName, String password) throws Exception;

    List<Map<String, Object>> auth(String userId) throws Exception;

    Boolean isExist(String username) throws Exception;

}
