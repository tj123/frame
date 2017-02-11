package com.shundian.frame.api.po.sys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tsys_function_module")
public class FunctionModulePo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private Short uid;
	
	private String functionId;

	@Column(name = "class")
	private String clazz;
	
	private String name;

}
