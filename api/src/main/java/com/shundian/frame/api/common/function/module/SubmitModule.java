package com.shundian.frame.api.common.function.module;


import com.shundian.lib.function.ModuleType;

public class SubmitModule implements ModuleType {

	@Override
	public Short getId() {
		return 5;
	}

	public String getName() {
		return "提交";
	}

}
