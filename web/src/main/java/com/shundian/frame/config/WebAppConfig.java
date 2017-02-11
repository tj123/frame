package com.shundian.frame.config;

import com.shundian.frame.api.common.FakeGlobalSession;
import com.shundian.lib.authorize.interceptor.AuthorizeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * WebAppp全局配置
 * @author CaoJian
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private FakeGlobalSession session;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链  
        // addPathPatterns 用于添加拦截规则  
        // excludePathPatterns 用于排除拦截  
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
		AuthorizeInterceptor interceptor = new AuthorizeInterceptor();
		interceptor.setGlobalSession(session);
		registry.addInterceptor(interceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
	}

}
