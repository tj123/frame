package com.github.tj123.frame.api.service;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
public interface UserService {

    Map<String, Object> test() throws Exception;

    /**
     * 列出用户列表
     *
     * @return
     * @throws Exception
     */
    Map<String, Object> list() throws Exception;

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     * @throws Exception
     */
    Map<String, Object> auth(String userId) throws Exception;
}
