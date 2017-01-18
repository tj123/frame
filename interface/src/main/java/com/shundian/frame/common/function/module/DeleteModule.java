package com.shundian.frame.common.function.module;


import com.shundian.lib.function.ModuleType;

public class DeleteModule implements ModuleType {

	@Override
	public Short getId() {
		return 2;
	}

	public String getName() {
		return "刪除";
	}

}
