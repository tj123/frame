package com.shundian.lib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;


/**
 * 通过ajax返回的对象
 *
 * @param <T>
 * @author TJ
 */
public class Result<T> extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    Log log = LogFactory.getLog(Result.class);

    public Result() {
        setStatus(false);
    }

    public Result(String msg) {
        this(false, msg);
    }

    public Result(boolean status) {
        setStatus(status);
    }

    public Result(boolean status, String msg) {
        setStatus(status);
        setMsg(msg);
    }

    /**
     * 设置状态
     */
    public Result<T> setStatus(boolean status) {
        put("state", status);
        return this;
    }

    /**
     * 放置消息
     */
    public Result<T> setMsg(String msg) {
        put("msg", msg);
        return this;
    }

    /**
     * 返回正确
     */
    public Result<T> ok(String msg) {
        setMsg(msg);
        return ok();
    }

    /**
     * 返回正确
     */
    public Result<T> ok() {
        return setStatus(true);
    }

    /**
     * 返回正确
     */
    public Result<T> ok(T data) {
        setData(data);
        return ok();
    }

    /**
     * 返回正确
     */
    public Result<T> ok(T data, String msg) {
        setData(data);
        setMsg(msg);
        return ok();
    }

    /**
     * 返回错误
     */
    public Result<T> error(String msg) {
        setMsg(msg);
        return error();
    }

    /**
     * 返回错误
     */
    public Result<T> error() {
        return setStatus(false);
    }

    /**
     * 返回错误
     */
    public Result<T> error(T data) {
        setData(data);
        return error();
    }

    /**
     * 返回错误
     */
    public Result<T> error(T data, String msg) {
        setData(data);
        setMsg(msg);
        return error();
    }

//    /**
//     * 返回错误
//     */
//    public Result<T> error(ErrorCodeEnum error) {
//        put("errorCode", error.getCode());
//        setMsg(error.getMessage());
//        return error();
//    }

//    /**
//     * 返回错误
//     */
//    public Result<T> error(NotValidException e) {
//        if (e.hasError())
//            put("error", e.error());
//        setMsg(e.getMessage());
//        if(log.isErrorEnabled()){
//            log.error(e);
//        }
//        return error();
//    }

//    /**
//     * 返回错误
//     */
//    public Result<T> error(AlertError e) {
//        put("alert", e.getMessage());
//        return error();
//    }

//    /**
//     * 返回错误
//     */
//    public Result<T> error(ToastError e) {
//        put("toastError",e.getMessage());
//        return error();
//    }

    /**
     * 放置数据
     */
    public Result<T> setData(T data) {
        put("data", data);
        return this;
    }

    /**
     * 获取状态
     */
    public boolean isStatus() {
        return (Boolean) get("state");
    }

    /**
     * 获取消息
     */
    public String getMsg() {
        return (String) get("msg");
    }

    /**
     * 获取数据
     */
    @SuppressWarnings("unchecked")
    public T getData() {
        return (T) get("data");
    }

    /**
     * 页面重定向
     * @param url
     * @return
     */
//    public Result<T> redirect(String url){
//        error(ErrorCodeEnum.NEED_REDIRECT).put("url",url);
//        return this;
//    }

}