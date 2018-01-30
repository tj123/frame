package com.github.tj123.frame.web.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeListener;
import com.github.tj123.common.auth.AuthorizeSessionImpl;
import com.github.tj123.common.auth.exception.NeedLoginException;
import com.github.tj123.frame.api.service.SUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateSession implements AuthorizeListener {

    @Autowired
    AuthorizeSessionImpl authorizeSession;

    @Autowired
    Session session;

    @Reference
    SUserService userService;

    public void onUpdateAuthorization() {
        try {
            authorizeSession.setAuth(userService.auth(session.getUserId()));
        } catch (Exception e) {
            log.error("auth error", e);
        }
    }

    public void onUpdateSession(String userId) throws Exception {
        try {
            session.setUserId(userId);
            session.fromMap(userService.session(userId));
        } catch (Exception e) {
            throw new NeedLoginException();
        }
    }
}
