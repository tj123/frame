package com.shundian.frame.api.service.sys;

import com.shundian.lib.Page;
import com.shundian.lib.PageResult;

import java.util.Map;

/**
 *
 */
public interface DepartmentService {


    PageResult<Map<String,Object>> list(Page page) throws Exception;

}
