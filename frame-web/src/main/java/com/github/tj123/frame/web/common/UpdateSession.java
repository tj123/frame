package com.github.tj123.frame.web.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeListener;
import com.github.tj123.common.auth.AuthorizeSessionImpl;
import com.github.tj123.frame.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class UpdateSession implements AuthorizeListener {

    @Autowired
    AuthorizeSessionImpl authorizeSession;

    @Autowired
    Session session;

    @Reference
    UserService userService;

    public void onUpdateAuthorization() {
        try {
            authorizeSession.setAuth(userService.auth(session.getUserId()));
        } catch (Exception e) {
            log.error("auth error", e);
        }
    }

    public void onUpdateSession(String userId) {
        try {
            Map<String, Object> map = userService.getInfo(userId);
            session.setUserId(userId);
            session.setDepId((String) map.get("departmentId"));
            session.setAreaId("0");
            session.setName((String) map.get("name"));
        } catch (Exception e) {
            log.error("session error", e);
        }
    }
}
