package com.shundian.frame.conf;

import com.github.tj123.db.MysqlTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DbTemplateConfig {

	@Autowired
	DataSource dataSource;

	@Bean(name = "mysqlTemplate")
	public MysqlTemplate mysqlTemplateBean(){
		MysqlTemplate mysqlTemplate = new MysqlTemplate();
		mysqlTemplate.setDataSource(dataSource);
		return mysqlTemplate;
	}



}
