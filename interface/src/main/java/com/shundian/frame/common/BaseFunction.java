package com.shundian.frame.common;


import com.shundian.frame.envm.ProjectTypeEnum;
import com.shundian.lib.function.FunctionType;

/**
 * 项目功能基类
 */
public abstract class BaseFunction implements FunctionType<ProjectTypeEnum> {

	public ProjectTypeEnum getProject() {
		return ProjectTypeEnum.FRAME;
	}

}
