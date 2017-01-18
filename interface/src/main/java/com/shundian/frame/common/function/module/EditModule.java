package com.shundian.frame.common.function.module;


import com.shundian.lib.function.ModuleType;

public class EditModule implements ModuleType {

	@Override
	public Short getId() {
		return 3;
	}

	public String getName() {
		return "修改";
	}

}
