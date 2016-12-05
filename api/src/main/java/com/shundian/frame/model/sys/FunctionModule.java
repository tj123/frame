package com.shundian.frame.model.sys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "tsys_function_module")
public class FunctionModule{

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private Short uid;
	
	private String functionId;

	@Column(name = "class")
	private String clazz;

	@Column(name = "key_")
	private String key;
	
	private String name;

}
