package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeType;
import com.github.tj123.common.auth.annotation.Authorize;
import com.github.tj123.frame.api.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */

@RestController
@RequestMapping("/sys")
public class MainController {

    @Reference
    UserService userService;

    @Authorize(AuthorizeType.ALL)
    @RequestMapping
    public Map<String,Object> test() throws Exception {
        return userService.test();
    }




}
