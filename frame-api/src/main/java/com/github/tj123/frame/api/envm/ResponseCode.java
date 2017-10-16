package com.github.tj123.frame.api.envm;

/**
 * Created by TJ on 2017/10/16.
 */
public enum ResponseCode {

    UNAUTHORIZED(401, "没有访问权限"),
    SERVER_ERROR(500, "服务器错误"),
    VALIDATE_ERROR(901, "验证错误"),
    NOT_LOGIN(902, "未登录");

    private final int code;

    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
