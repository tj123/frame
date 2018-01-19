package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthProperties;
import com.github.tj123.common.auth.AuthorizeSessionImpl;
import com.github.tj123.common.auth.AuthorizeType;
import com.github.tj123.common.auth.ValidateConfig;
import com.github.tj123.common.auth.annotation.Authorize;
import com.github.tj123.common.auth.annotation.Module;
import com.github.tj123.common.auth.token.AccessToken;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.dto.UserDto;
import com.github.tj123.frame.api.po.UserPo;
import com.github.tj123.frame.api.service.DictService;
import com.github.tj123.frame.api.service.UserService;
import com.github.tj123.frame.web.common.ControllerUtil;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.UpdateSession;
import com.github.tj123.frame.web.common.unit.Add;
import com.github.tj123.frame.web.common.unit.Edit;
import com.github.tj123.frame.web.common.unit.User;
import com.github.tj123.frame.web.config.ProjectProperties;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    AuthorizeSessionImpl authorizeSession;

    @PostMapping("/login")
    @Authorize(AuthorizeType.ALL)
    public Map<String, Object> login(HttpServletRequest request, String username, String password, Boolean keep) throws Exception {
        updateSession.onUpdateSession(userService.login(username, password));
        updateSession.onUpdateAuthorization();
        Map<String, Object> map = new HashMap();
        ValidateConfig config = authorizeSession.getConfig(request);
        if (Boolean.TRUE.equals(keep) && config.isEnable()) {
            AccessToken token = new AccessToken(session.getUserId(), "frame", authProperties.getKey(), authProperties.getIv());
            if (config.isUserAgent()) {
                token.setUserAgent(request.getHeader("User-Agent"));
            }
            if (config.isIp()) {
                token.setIp(ControllerUtil.getIp(request));
            }
            map.put("key", token.encrypt());
        }
        return map;
    }

    @GetMapping("/dict")
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

    @Module({User.class, Edit.class})
    @PostMapping("/user/update")
    public void userUpdate(@RequestBody UserDto user) throws Exception {
        UserPo userPo = user.toPo();
        userService.update(userPo);
    }

    @GetMapping("/auth")
    public List<Map<String, Object>> auth() throws Exception {
        return userService.auth(session.getUserId());
    }

    @Autowired
    ProjectProperties properties;

    @Authorize(AuthorizeType.ALL)
    @GetMapping("/version")
    public String version(){
        return properties.getVersion() + "  hahahaaa";
    }

}
