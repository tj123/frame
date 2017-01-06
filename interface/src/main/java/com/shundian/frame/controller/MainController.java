package com.shundian.frame.controller;

import com.shundian.frame.api.dto.sys.UserDto;
import com.shundian.frame.api.service.sys.UserService;
import com.shundian.lib.Result;
import com.shundian.lib.common.bean.validate.impl.NotValidException;
import com.shundian.lib.permission.Permission;
import com.shundian.lib.permission.PermissionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
@Permission(PermissionTypeEnum.LOGIN)
public class MainController {

    @Autowired
    private UserService service;

//    @RequestMapping
//    public Result<?> list(Page page) {
//        Result<PageResult<RolePo>> result = new Result<PageResult<RolePo>>();
//        try {
//            result.ok(service.list(page));
//        } catch (Exception e) {
//            result.error("错误", log, e);
//        }
//        return result;
//    }

    @RequestMapping("/login")
    @Permission(PermissionTypeEnum.ALL)
    public Result<?> login(UserDto dto, HttpServletRequest request) {
        Result<Map<String,Object>> result = new Result<Map<String,Object>>();
        try {
            dto.validate("username","password");
            result.ok(service.login(dto.getUsername(),dto.getPassword(),request));
        } catch (NotValidException e) {
            result.error(e);
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/menus")
    public Result<?> addFunc() {
        Result<Object> result = new Result<Object>();
        try {
//            service.addFunc(role,funcs);
            result.ok();
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

}
