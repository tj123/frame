package com.shundian.frame.api.po.sys;

import com.shundian.frame.api.envm.ProjectTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tsys_function")
public class FunctionPo implements Serializable{

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private Short uid;

	private String parentId;

	@Column(name = "class")
	private String clazz;
	
	private String name;
	
	private String uiSref;

	@Column(name = "order_")
	private Integer order;
	
	private String icon;
	
	private String alias;
	
	private Boolean isShow;
	
	private ProjectTypeEnum project;


}
