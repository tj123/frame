package com.shundian.frame.api.common;


import com.shundian.frame.api.envm.ProjectTypeEnum;
import com.shundian.lib.function.FunctionType;

/**
 * 项目功能基类
 */
public abstract class BaseFunction implements FunctionType<ProjectTypeEnum> {

	public ProjectTypeEnum getProject() {
		return ProjectTypeEnum.FRAME;
	}

}
