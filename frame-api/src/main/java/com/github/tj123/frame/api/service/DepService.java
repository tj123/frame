package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
public interface DepService {

    /**
     * 列出用户列表
     *
     * @return
     * @throws Exception
     * @param request
     */
    PageResponse<Map<String,Object>> list(PageRequest request) throws Exception;


//    void add(UserPo userPo) throws Exception;

//    void update(UserPo userPo) throws Exception;
}
