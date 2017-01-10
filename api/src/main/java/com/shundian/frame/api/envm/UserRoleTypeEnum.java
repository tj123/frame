package com.shundian.frame.api.envm;

import com.shundian.lib.session.UserRoleType;

/**
 * Created by TJ on 2016/12/5.
 */
public enum UserRoleTypeEnum implements UserRoleType {

    ADMIN("ADN","管理员"),
    DEP_ADMIN("D_ADN","部门管理员"),
    KEYBOARDER("KBDR","录入员"),
    INSPECTOR("ISPCTR","巡查员"),
    ASSESSOR("ASER","审核员"),
    DEP_MANAGER("D_MG","部门经理");

    private String key;

    private String value;

    UserRoleTypeEnum(String key, String value){
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