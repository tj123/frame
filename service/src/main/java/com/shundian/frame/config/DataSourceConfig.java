package com.shundian.frame.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

//	@Autowired
//	DataSource dataSource;
//
//	@Bean(name = "mysqlTemplate")
//	public MysqlTemplate mysqlTemplateBean(){
//		MysqlTemplate mysqlTemplate = new MysqlTemplate();
//		mysqlTemplate.setDataSource(dataSource);
//		return mysqlTemplate;
//	}
  
  @Bean(name = "dataSource",autowire = Autowire.BY_NAME)
  public DruidDataSource dataSourceBean(){
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver---");
    dataSource.setUrl("jdbc:mysql:///demo?characterEncoding=utf-8");
    dataSource.setUsername("root");
    dataSource.setPassword("111111");
    dataSource.setMaxActive(1000);
    dataSource.setMaxActive(100);
    return dataSource;
  }
}
