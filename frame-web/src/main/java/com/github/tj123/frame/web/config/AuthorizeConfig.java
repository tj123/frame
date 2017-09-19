package com.github.tj123.frame.web.config;

import com.github.tj123.common.auth.exception.AuthorizeFailedException;
import com.github.tj123.common.auth.exception.NeedLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by TJ on 2017/9/19.
 */
@ControllerAdvice
public class AuthorizeConfig {

    @ResponseBody
    @ExceptionHandler(NeedLoginException.class)
    public String needLogin() {
        return "你要登录了";
    }

    @ResponseBody
    @ExceptionHandler(AuthorizeFailedException.class)
    public String authorizeFailed() {
        return "你没有权限";
    }

}
