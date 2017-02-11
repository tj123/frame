package com.shundian.frame.api.common.function.module;


import com.shundian.lib.function.ModuleType;

public class ScanModule implements ModuleType {

	@Override
	public Short getId() {
		return 6;
	}

	public String getName() {
		return "扫描";
	}

}
