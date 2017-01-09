package com.shundian.frame.config;

import com.shundian.frame.common.GlobalSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * globalSession 自动注入
 */
@Configuration
public class GlobalSessionConfig {
  
  @Bean
  @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
  GlobalSession globalSession(){
    return new GlobalSession();
  }
  
  
}
