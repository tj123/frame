package com.shundian.frame.common.function.module;


import com.shundian.lib.function.ModuleType;

public class AddModule implements ModuleType {

	@Override
	public Short getId() {
		return 1;
	}

	public String getKey() {
		return "ADD";
	}

	public String getName() {
		return "添加";
	}

}
