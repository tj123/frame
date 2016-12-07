package com.shundian.frame.api.envm;

import com.shundian.lib.function.ProjectType;

/**
 * Created by TJ on 2016/12/5.
 */
public enum ProjectTypeEnum implements ProjectType {

    FRAME("FRM","框架");

    ProjectTypeEnum(String key,String name){
        this.key = key;
        this.name = name;
    }
    private String key;

    private String name;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }
}
