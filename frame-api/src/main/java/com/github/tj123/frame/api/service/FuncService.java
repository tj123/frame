package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.common.auth.DbFunction;

import java.util.List;
import java.util.Map;

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
     * @param scans
     * @throws Exception
     */
    void scan(List<DbFunction> scans) throws Exception;


    /**
     * 列出所有的
     *
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> all() throws Exception;

}
