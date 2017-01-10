package com.shundian.frame.api.service.sys;

import com.shundian.frame.api.common.GlobalSession;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;

import java.util.Map;

/**
 *
 */
public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     * @throws Exception
     */
    Map<String, Object> login(String username, String password, GlobalSession session) throws Exception;


    PageResult<Map<String,Object>> list(Page page) throws Exception;
}
