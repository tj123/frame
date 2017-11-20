package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthProperties;
import com.github.tj123.common.auth.AuthorizeType;
import com.github.tj123.common.auth.annotation.Authorize;
import com.github.tj123.common.auth.annotation.Module;
import com.github.tj123.common.auth.token.AccessToken;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.dto.UserDto;
import com.github.tj123.frame.api.po.UserPo;
import com.github.tj123.frame.api.service.DictService;
import com.github.tj123.frame.api.service.UserService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.UpdateSession;
import com.github.tj123.frame.web.common.unit.Add;
import com.github.tj123.frame.web.common.unit.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Authorize(AuthorizeType.ALL)
//@Function(User.class)
@RestController
@RequestMapping("/sys")
public class MainController {

    @Reference
    UserService userService;

    @Reference
    DictService dictService;

    @Autowired
    Session session;

    @Autowired
    UpdateSession updateSession;

    @Autowired
    AuthProperties authProperties;

    @PostMapping("/login")
    @Authorize(AuthorizeType.ALL)
    public Map<String, Object> login(String username, String password,Boolean keep) throws Exception {
        updateSession.onUpdateSession(userService.login(username, password));
        Map<String, Object> map = new HashMap();
        AccessToken token = new AccessToken(session.getUserId(), "frame",
                authProperties.getKey(), authProperties.getIv());
        if(Boolean.TRUE.equals(keep)){
            map.put("key", token.encrypt());
        }
        return map;
    }

    @PostMapping("/dict")
    @Authorize(AuthorizeType.LOGIN)
    public List<Map<String, Object>> dict(String type, String code) throws Exception {
        return dictService.getDict(type, code, session.getDepId());
    }

    @Module(User.class)
    @GetMapping("/users")
    public PageResponse<UserPo> userList(@RequestParam Map<String, Object> page) throws Exception {
        return userService.list(PageRequest.create(page));
    }

    @Module({User.class, Add.class})
    @PostMapping("/user")
    public void userAdd(@RequestBody UserDto user) throws Exception {
        UserPo userPo = user.toPo();
        userPo.setPassword("sfaasdfasdfsd");
        userService.add(userPo);
    }

    @Module({User.class, Add.class})
    @PostMapping("/user/update")
    public void userUpdate(@RequestBody UserDto user) throws Exception {
        UserPo userPo = user.toPo();
        userService.update(userPo);
    }

//    @Module(Dep.class)
//    @RequestMapping(value = "/dep" ,method = RequestMethod.POST)
//    public Map<String,Object> depList() throws Exception{
//        return userService.list();
//    }

    @Authorize(AuthorizeType.LOGIN)
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Map<String, Object> auth() throws Exception {
        return userService.auth(session.getUserId());
    }


}
