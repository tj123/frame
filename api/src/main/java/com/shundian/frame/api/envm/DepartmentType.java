package com.shundian.frame.api.envm;

import com.shundian.lib.envm.EnumType;

/**
 * Created by TJ on 2016/12/5.
 */
public enum DepartmentType implements EnumType {
    /**
     * Food and Drug Administration
     */
    FDA("FDA", "食药局"),

    /**
     * Education Bureau
     */
    EB("EB", "教育局"),

    /**
     * public security bureau
     */
    PSB("PSB", "公安厅/公安局/派出所");

    private final String key;
    private final String value;

    DepartmentType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
