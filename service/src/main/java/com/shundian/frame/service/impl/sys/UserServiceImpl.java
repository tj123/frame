package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Map<String, Object> login(String username, String password, HttpServletRequest request) throws Exception {
        return null;
    }

    public void updateSession(HttpServletRequest request){

    }
}
