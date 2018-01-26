package com.github.tj123.frame.web.common;


import com.github.tj123.common.auth.AuthorizeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {


    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


//        if (handler instanceof ResourceHttpRequestHandler)
//            return true;
//        if (request instanceof DefaultMultipartHttpServletRequest)
//            return true;

        String func = "";
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            func = AuthorizeUtils.commonName(method);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        log.info("{}({}) 访问 {}({} {})", session.getName(), ControllerUtil.getIp(request), func, request.getMethod(), ControllerUtil.getVisitUrl(request));


        return true;
    }
}
