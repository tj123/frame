package com.shundian.frame.common.function.module;


import com.shundian.lib.function.ModuleType;

public class ViewModule implements ModuleType {

	@Override
	public Short getId() {
		return 4;
	}

	public String getName() {
		return "查看";
	}

}
