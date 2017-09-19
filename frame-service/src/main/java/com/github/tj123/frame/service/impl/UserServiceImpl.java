package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Service
public class UserServiceImpl implements UserService{
    public Map<String, Object> test() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("aaa","haha");
        return map;
    }
}
