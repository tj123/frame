package com.shundian.frame.api.service.sys;

import com.shundian.frame.api.entity.sys.AuthFunction;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    Map<String, Object> login(String username, String password) throws Exception;


    PageResult<Map<String,Object>> list(Page page) throws Exception;

    /**
     * 获取用户所具有的权限
     * @return
     * @throws Exception
     */
    List<AuthFunction> getAuths(String userId) throws Exception;
}
