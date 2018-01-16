package com.github.tj123.frame.web.config;

import com.github.tj123.common.auth.exception.AuthorizeFailedException;
import com.github.tj123.common.auth.exception.NeedLoginException;
import com.github.tj123.frame.api.common.exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public String needLogin() {
        return "未登录";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizeFailedException.class)
    public String authorizeFailed() {
        return "没有访问权限";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String innerError(Exception e) {
        if (e instanceof MessageException) {
            String message = e.getMessage();
            if (log.isWarnEnabled()) {
                log.warn("{} : {}", message, e.getStackTrace()[0]);
            }
            return message;
        }
        if (log.isErrorEnabled()) {
            log.error("请求错误", e);
        }
        return "请求错误";
    }

}
