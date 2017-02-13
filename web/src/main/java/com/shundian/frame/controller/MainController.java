package com.shundian.frame.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shundian.frame.api.common.FakeGlobalSession;
import com.shundian.frame.api.common.function.sys.SystemManageFunction;
import com.shundian.frame.api.dto.sys.UserDto;
import com.shundian.frame.api.entity.sys.AuthFunction;
import com.shundian.frame.api.service.sys.UserService;
import com.shundian.frame.common.GlobalSession;
import com.shundian.lib.Result;
import com.shundian.lib.authorize.Authorize;
import com.shundian.lib.authorize.AuthorizeType;
import com.shundian.lib.common.bean.validate.impl.NotValidException;
import com.shundian.lib.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys")
@Authorize(AuthorizeType.LOGIN)
@Function(SystemManageFunction.class)
public class MainController {

    @Reference(version = "1.0.0")
    private UserService service;

    @Autowired
    private GlobalSession session;


    @RequestMapping("/login")
    @Authorize(AuthorizeType.ALL)
    public Result<?> login(UserDto dto) {
        Result<Map<String, Object>> result = new Result<Map<String, Object>>();
        try {
            dto.validate("username", "password");
            Map<String, Object> resp = service.login(dto.getUsername(), dto.getPassword());
            result.ok((Map<String,Object>)resp.get("response"));
            FakeGlobalSession fakeSession = (FakeGlobalSession) resp.get("_fakeSession");
            session.copy(fakeSession);
        } catch (NotValidException e) {
            result.error(e);
        } catch (Exception e) {
            result.error(log, e);
        }
        return result;
    }

    /**
     * 检测 session
     *
     * @return
     */
    @RequestMapping("/cksesn")
    public Result<?> checkSession() {
        return new Result<Object>().ok();
    }

    /**
     * 获取用户所具有的权限
     * @return
     */
    @RequestMapping("/auths")
    public Result<?> auths() {
        Result<List<AuthFunction>> result = new Result<List<AuthFunction>>();
        try {
            result.ok(service.getAuths(session.getUserId()));
        } catch (Exception e) {
            result.error(log, e);
        }
        return result;
    }


    @RequestMapping("/menus")
    public Result<?> menus() {
        Result<Object> result = new Result<Object>();
        System.out.println(session.getRealName());
        try {
//            service.addFunc(role,funcs);
            Map<String, Object> map = new HashMap<String, Object>();
            System.out.println(session.getRealName());
            System.out.println(session.getUserId());
            System.out.println(session.getDepartmentId());
            System.out.println(session.getAreaId());
            System.out.println(session.getAreaLevel());
            System.out.println(session.getParentAreaId());
            map.put("getRealName", session.getRealName());
            map.put("getUserId", session.getUserId());
            map.put("getDepartmentId", session.getDepartmentId());
            map.put("getAreaId", session.getAreaId());
            map.put("getAreaLevel", session.getAreaLevel());
            map.put("getParentAreaId", session.getParentAreaId());
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
