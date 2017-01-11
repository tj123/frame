package com.shundian.frame.config;

import com.shundian.lib.authorize.interceptor.AuthorizeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * WebAppp全局配置
 * @author CaoJian
 *
 */
//@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链  
        // addPathPatterns 用于添加拦截规则  
        // excludePathPatterns 用于排除拦截  
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new AuthorizeInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
	}

}