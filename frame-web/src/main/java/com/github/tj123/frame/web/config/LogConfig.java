package com.github.tj123.frame.web.config;

import com.github.tj123.frame.web.common.LogInterceptor;
import com.github.tj123.frame.web.common.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LogConfig extends WebMvcConfigurerAdapter{


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    Session session;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LogInterceptor logInterceptor = new LogInterceptor();
        logInterceptor.setSession(session);
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(logInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
