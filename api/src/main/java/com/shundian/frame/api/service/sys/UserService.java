package com.shundian.frame.api.service.sys;

import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.Map;

/**
 *
 */
public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     * @throws Exception
     */
    Map<String, Object> login(String username, String password, HttpServletRequest request) throws Exception;


    PageResult<Map<String,Object>> list(Page page) throws Exception;
}
