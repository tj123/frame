package com.shundian.frame.api.po.sys;

import com.shundian.frame.api.envm.ProjectTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

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

	private String entry;

	@Column(name = "order_")
	private Integer order;
	
	private String icon;
	
	private String alias;
	
	private Boolean isShow;
	
	private ProjectTypeEnum project;

	private Date operateTime;

}
