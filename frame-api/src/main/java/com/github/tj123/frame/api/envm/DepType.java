package com.github.tj123.frame.api.envm;

import com.github.tj123.common.convert.ConvertibleEnum;

/**
 * Created by TJ on 2017/10/24.
 */
public enum DepType implements ConvertibleEnum {

    ADMIN("ADM", "系统管理"),

    CHIEF("CHF", "省/市/(区/县) 机关"),

    DETECT("DET", "省/市 检测机构"),

    EDUCATION("EDU","省/市 教育局"),

    FDA("FDA", "省/市/(区/县)/(镇/街)/(社区/村) 食药 监局/所");

    private final String key;
    private final String name;

    DepType(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
