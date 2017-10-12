package com.github.tj123.frame.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Profile("dev")
@Configuration
public class VisitConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8000"); // 1
        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedOrigin("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        corsConfiguration.setAllowCredentials(true);
        // header('Access-Control-Allow-Origin:*');
// 响应类型
        //header('Access-Control-Allow-Methods:POST');
// 响应头设置
        // header('Access-Control-Allow-Headers:x-requested-with,content-type');
//        corsConfiguration.addAllowedOrigin("*"); // 1
//        corsConfiguration.addAllowedHeader("*"); // 2
//        corsConfiguration.addAllowedMethod("*"); // 3
        source.registerCorsConfiguration("/**", corsConfiguration); // 4
        return new CorsFilter(source);
    }
}