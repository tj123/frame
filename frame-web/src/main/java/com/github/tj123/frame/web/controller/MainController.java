package com.github.tj123.frame.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeSessionImpl;
import com.github.tj123.common.auth.AuthorizeType;
import com.github.tj123.common.auth.annotation.Authorize;
import com.github.tj123.frame.api.service.DictService;
import com.github.tj123.frame.api.service.SUserService;
import com.github.tj123.frame.web.common.Session;
import com.github.tj123.frame.web.common.UpdateSession;
import com.github.tj123.frame.web.config.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class MainController {

    @Reference
    SUserService userService;

    @Reference
    DictService dictService;

    @Autowired
    Session session;

    @Autowired
    UpdateSession updateSession;

    @Autowired
    AuthorizeSessionImpl authorizeSession;

    @PostMapping("/login")
    @Authorize(AuthorizeType.ALL)
    public Map<String, Object> login(String username, String password, Boolean keep) throws Exception {
        updateSession.onUpdateSession(userService.login(username, password));
        updateSession.onUpdateAuthorization();
        Map<String, Object> map = new HashMap();
        if (Boolean.TRUE.equals(keep) && authorizeSession.isTokenEnabled()) {
            map.put("key", authorizeSession.distributeToken(session.getUserId()));
        }
        return map;
    }

    @GetMapping("/dict")
    @Authorize(AuthorizeType.LOGIN)
    public List<Map<String, Object>> dict(String type, String code) throws Exception {
        return dictService.getDict(type, code, session.getDepId());
    }

    @GetMapping("/auth")
    public List<Map<String, Object>> auth() throws Exception {
        return userService.auth(session.getUserId());
    }

    @Autowired
    ProjectProperties properties;

    @Authorize(AuthorizeType.ALL)
    @GetMapping("/version")
    public String version() {
        return properties.getVersion() + "  hahahaaa";
    }

}
