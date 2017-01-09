package com.shundian.frame.common;

import com.shundian.frame.api.envm.UserTypeEnum;
import com.shundian.frame.api.mis.AreaLevel;
import com.shundian.frame.api.mis.DepType;
import com.shundian.lib.session.LibGlobalSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Session 对象；后台登录后才能使用
 */
@Setter
@Getter
public class GlobalSession implements LibGlobalSession, Serializable {
	
	private static final long serialVersionUID = 7506887334057350480L;

	/**
	 * Session的Key值
	 */
	public static final String SESSION_KEY = "_GLOBAL_SESSION";

	/**
	 * 当前用户Id  
	 */
	private String userId;
	
	/**
	 * 用户类型
	 */
	private UserTypeEnum userType;
	
	/**
	 * 用户真实名称
	 */
	private String realName;
	
	/**
	 *用户所在部门Id
	 */
	private String departmentId;
	
	/**
	 * 部门编码
	 */
	private String departmentCode;

	/**
	 *用户所在部门名称
	 */
	private String departmentName;
	
	/**
	 * 区域ID
	 */
	private String areaId;
	
	/**
	 * 父级区域ID
	 */
	private String parentAreaId;
	
	/**
	 * 用户所在部门的上级部门Id
	 */
	private String parentDepartmentId;
	
	private DepType departmentType;
	
	private AreaLevel areaLevel;
	
	@Override
	public String getKey() throws Exception {
		return null;
	}
	
	@Override
	public String getUserId() throws Exception {
		return null;
	}
}