package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeType;
import com.github.tj123.common.auth.annotation.Authorize;
import com.github.tj123.common.auth.annotation.Function;
import com.github.tj123.common.auth.annotation.Module;
import com.github.tj123.frame.api.service.UserService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.unit.Dep;
import com.github.tj123.frame.web.common.unit.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Function
@RestController
@RequestMapping("/sys")
public class MainController {

    @Reference
    UserService userService;

    @Autowired
    Session session;

    @Authorize(AuthorizeType.ALL)
    @RequestMapping
    public Map<String,Object> test() throws Exception {
        return userService.test();
    }

    @Module(User.class)
    @RequestMapping(value = "/user" ,method = RequestMethod.POST)
    public Map<String,Object> userList() throws Exception{
        return userService.list();
    }

    @Module(Dep.class)
    @RequestMapping(value = "/dep" ,method = RequestMethod.POST)
    public Map<String,Object> depList() throws Exception{
        return userService.list();
    }

    @Authorize(AuthorizeType.LOGIN)
    @RequestMapping(value = "/auth" ,method = RequestMethod.POST)
    public Map<String,Object> auth() throws Exception{
        return userService.auth(session.getUserId());
    }




}
