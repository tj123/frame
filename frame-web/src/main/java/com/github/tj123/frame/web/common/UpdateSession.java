package com.github.tj123.frame.web.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tj123.common.auth.AuthorizeSessionImpl;
import com.github.tj123.common.auth.UpdateAuthorizeListener;
import com.github.tj123.common.auth.UpdateSessionListener;
import com.github.tj123.frame.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Slf4j
@Component
public class UpdateSession implements UpdateSessionListener, UpdateAuthorizeListener {

    @Autowired
    AuthorizeSessionImpl authorizeSession;

    @Autowired
    Session session;

    @Reference
    UserService userService;

    public void onUpdateAuthorization() {
        try {
            Map<String, Object> auth = userService.auth(session.getUserId());

        } catch (Exception e) {
            log.error("auth error",e);
        }

        System.out.println("update auth");
    }

    public void onUpdateSession(String userId) {
        try {
            Map<String,Object> map = userService.getInfo(userId);
            session.setUserId(userId);
            session.setDepId((String) map.get("departmentId"));
        } catch (Exception e) {
            log.error("session error",e);
        }
        System.out.println("update session");
    }
}
