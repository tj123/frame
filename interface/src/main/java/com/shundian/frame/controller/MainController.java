package com.shundian.frame.controller;

import com.shundian.frame.api.common.GlobalSession;
import com.shundian.frame.api.dto.sys.UserDto;
import com.shundian.frame.api.service.sys.UserService;
import com.shundian.lib.Result;
import com.shundian.lib.common.bean.validate.impl.NotValidException;
import com.shundian.lib.permission.Permission;
import com.shundian.lib.permission.PermissionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
@Permission(PermissionTypeEnum.LOGIN)
public class MainController {

    @Autowired
    private UserService service;

    @Autowired
    private GlobalSession session;

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
    public Result<?> login(UserDto dto) {
        System.out.println(session);
        Result<Map<String, Object>> result = new Result<Map<String, Object>>();
        try {
            dto.validate("username", "password");
            result.ok(service.login(dto.getUsername(), dto.getPassword(), session));
            System.out.println(session);
        } catch (NotValidException e) {
            result.error(e);
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

    @RequestMapping("/menus")
    public Result<?> menus() {
        Result<Object> result = new Result<Object>();
        System.out.println(session.getRealName());
        try {
//            service.addFunc(role,funcs);
            Map<String,Object> map = new HashMap<String,Object>();
            System.out.println(session.getRealName());
            System.out.println(session.getUserId());
            System.out.println(session.getDepartmentId());
            System.out.println(session.getAreaId());
            System.out.println(session.getAreaLevel());
            System.out.println(session.getParentAreaId());
            map.put("getRealName",session.getRealName());
            map.put("getUserId",session.getUserId());
            map.put("getDepartmentId",session.getDepartmentId());
            map.put("getAreaId",session.getAreaId());
            map.put("getAreaLevel",session.getAreaLevel());
            map.put("getParentAreaId",session.getParentAreaId());
            result.ok(map);
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }


    @RequestMapping("/test")
    public Object test() {
        System.out.println(session);
        //if (1 == 1)
            //return session;
        Result<Object> result = new Result<Object>();
        session.setRealName(Math.random() + "");
        System.out.println(session.getRealName());
        try {
//            service.addFunc(role,funcs);
            result.ok();
        } catch (Exception e) {
            result.error("错误", log, e);
        }
        return result;
    }

}
