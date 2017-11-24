package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.po.sys.RolePo;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
public interface RoleService {

    /**
     * 列出用户列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    PageResponse<Map<String, Object>> list(PageRequest request) throws Exception;

    void add(RolePo po, List<String> roles) throws Exception;
}
