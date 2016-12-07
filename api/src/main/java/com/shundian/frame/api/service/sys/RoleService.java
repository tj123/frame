package com.shundian.frame.api.service.sys;

import com.shundian.frame.api.po.sys.Role;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;

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

    /**
     * 添加角色
     * @param role
     * @throws Exception
     */
    void add(Role role) throws Exception;
}
