package com.shundian.frame.api.sys;

import com.shundian.frame.model.sys.Role;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.function.FunctionType;
import com.shundian.lib.function.ModuleType;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface RoleService {

    /**
     * 列出所有功能
     * @return
     * @throws Exception
     */
    PageResult<Role> list(Page page) throws Exception;

    /**
     * 根据id列出
     * @return
     * @throws Exception
     */
    Role list(String id) throws Exception;
}
