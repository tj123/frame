package com.github.tj123.frame.web.config;

import com.github.tj123.common.auth.exception.AuthorizeFailedException;
import com.github.tj123.common.auth.exception.NeedLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by TJ on 2017/9/19.
 */
@ControllerAdvice
public class AuthorizeConfig {

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ExceptionHandler(NeedLoginException.class)
    public String needLogin() {
        return "未登录";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizeFailedException.class)
    public String authorizeFailed() {
        return "没有访问权限";
    }

}
