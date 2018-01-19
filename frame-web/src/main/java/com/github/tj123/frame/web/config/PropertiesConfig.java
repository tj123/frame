package com.github.tj123.frame.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:project.properties")
@EnableConfigurationProperties(ProjectProperties.class)
public class PropertiesConfig {

}
