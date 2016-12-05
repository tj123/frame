package com.shundian.frame.model.sys;

import com.shundian.frame.envm.ProjectTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tsys_function")
public class Function implements Serializable{

	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private Short uid;

	private String parentId;

	@Column(name = "class")
	private String clazz;
	
	private String name;
	
	private String uiSref;
	
	private int order;
	
	private String icon;
	
	private String alias;
	
	private boolean isShow;
	
	private ProjectTypeEnum project;


}
