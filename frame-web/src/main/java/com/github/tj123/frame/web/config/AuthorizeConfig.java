package com.github.tj123.frame.web.config;

import com.github.tj123.common.auth.exception.AuthorizeFailedException;
import com.github.tj123.common.auth.exception.NeedLoginException;
import com.github.tj123.frame.api.common.exception.MessageException;
import com.github.tj123.frame.web.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseBody
@ControllerAdvice
public class AuthorizeConfig {

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ExceptionHandler(NeedLoginException.class)
    ErrorResponse needLogin() {
        return new ErrorResponse("未登录");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizeFailedException.class)
    ErrorResponse authorizeFailed() {
        return new ErrorResponse("没有访问权限");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    ErrorResponse validateError(BindException e) {
        return new ErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MessageException.class)
    ErrorResponse message(MessageException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ErrorResponse innerError(Exception e) {
        if (log.isErrorEnabled()) {
            log.error("请求错误", e);
        }
        return new ErrorResponse("请求错误");
    }

}
