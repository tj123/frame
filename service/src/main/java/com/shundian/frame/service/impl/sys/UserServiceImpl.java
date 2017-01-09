package com.shundian.frame.service.impl.sys;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shundian.frame.api.service.sys.UserService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.mapper.sys.UserMapper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public Map<String, Object> login(String username, String password, HttpServletRequest request) throws Exception {
        return null;
    }

    @Override
    public PageResult<Map<String, Object>> list(Page page) throws Exception {
        PageUtil<Map<String, Object>> pageUtil = new PageUtil<>();
        pageUtil.startPage(page);
        new ObjectMapper();
        return pageUtil.assembleResult(mapper.list(page.assembleSearch()));

    }


    public void updateSession(HttpServletRequest request){

    }
}
