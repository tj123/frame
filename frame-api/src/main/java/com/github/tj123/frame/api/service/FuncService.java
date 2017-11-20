package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
public interface FuncService {

    /**
     * 列出用户列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

    /**
     * 扫描功能
     *
     * @throws Exception
     * @param maps

     */
    void scan(List<Map<String, Object>> maps) throws Exception;


//    void add(UserPo userPo) throws Exception;

//    void update(UserPo userPo) throws Exception;
}
