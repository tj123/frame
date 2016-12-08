package com.shundian.frame.api.service.sys;

import com.shundian.frame.api.po.sys.RolePo;
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
    PageResult<RolePo> list(Page page) throws Exception;

    /**
     * 根据id列出
     * @return
     * @throws Exception
     */
    RolePo list(String id) throws Exception;

    /**
     * 添加角色
     * @param po
     * @throws Exception
     */
    void add(RolePo po) throws Exception;
}
