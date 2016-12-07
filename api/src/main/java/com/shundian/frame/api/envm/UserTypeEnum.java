package com.shundian.frame.api.envm;

import com.shundian.lib.session.UserType;

/**
 * Created by TJ on 2016/12/5.
 */
public enum UserTypeEnum implements UserType {

    ADMIN("ADN","管理员");

    private String key;

    private String value;

    UserTypeEnum(String key,String value){
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
