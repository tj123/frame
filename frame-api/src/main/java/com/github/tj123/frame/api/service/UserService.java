package com.github.tj123.frame.api.service;

import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.po.UserPo;

import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
public interface UserService {

    Map<String, Object> test() throws Exception;

    /**
     * @param userName
     * @param password
     * @return 用户 id
     * @throws Exception
     */
    String login(String userName,String password) throws Exception;

    /**
     * 列出用户列表
     *
     * @return
     * @throws Exception
     * @param request
     */
    PageResponse<UserPo> list(PageRequest request) throws Exception;

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> auth(String userId) throws Exception;

    void add(UserPo userPo) throws Exception;

    void update(UserPo userPo) throws Exception;

    /**
     * 更加 userid 获取所有用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    Map<String,Object> getInfo(String userId) throws Exception;
}
