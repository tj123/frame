package com.shundian.frame.common.function.module;


import com.shundian.lib.function.ModuleType;

public class SubmitModule implements ModuleType {

	@Override
	public Short getId() {
		return 5;
	}

	public String getKey() {
		return "SBMT";
	}

	public String getName() {
		return "提交";
	}

}
