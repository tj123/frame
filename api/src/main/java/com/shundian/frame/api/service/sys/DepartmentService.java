package com.shundian.frame.api.service.sys;

import com.shundian.lib.Page;
import com.shundian.lib.PageResult;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DepartmentService {


    /**
     * 列出部门
     *
     * @param page
     * @return
     * @throws Exception
     */
    PageResult<Map<String, Object>> list(Page page) throws Exception;

    /**
     * 根据名称搜部门
     *
     * @param name
     * @param limit
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> search(String name, int limit) throws Exception;
}
