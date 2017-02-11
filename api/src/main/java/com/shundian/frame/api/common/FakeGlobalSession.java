package com.shundian.frame.api.common;

import com.shundian.frame.api.envm.AreaLevel;
import com.shundian.lib.authorize.Authorization;
import com.shundian.lib.session.Session;
import com.shundian.lib.session.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;


/**
 * Session 对象；后台登录后才能使用
 */
@Slf4j
@Setter
@Getter
public class FakeGlobalSession implements Session, Serializable {

    private static final long serialVersionUID = 7506887334057350480L;

    /**
     * 当前用户Id
     */
    private String userId;

    /**
     * 用户类型
     */
    private List<UserRole> userRoleTypes;

    /**
     * 用户具有的权限
     */
    private Authorization authorization;

    /**
     * 用户真实名称
     */
    private String realName;

    /**
     * 用户所在部门Id
     */
    private String departmentId;

    /**
     * 部门编码
     */
    private String departmentCode;

    /**
     * 用户所在部门名称
     */
    private String departmentName;

    /**
     * 区域ID
     */
    private String areaId;

    /**
     * 父级区域ID
     */
    private String parentAreaId;

    /**
     * 用户所在部门的上级部门Id
     */
    private String parentDepartmentId;


    private AreaLevel areaLevel;

}