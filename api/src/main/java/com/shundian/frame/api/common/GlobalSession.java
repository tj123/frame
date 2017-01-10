package com.shundian.frame.api.common;

import com.shundian.frame.api.envm.AreaLevel;
import com.shundian.lib.session.LibGlobalSession;
import com.shundian.lib.session.UserRoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;


/**
 * Session 对象；后台登录后才能使用
 */
@Slf4j
@Setter
@Getter
@Component
@SessionScope
public class GlobalSession implements LibGlobalSession, Serializable {

    private static final long serialVersionUID = 7506887334057350480L;

    public void copy(GlobalSession session){
        try {
            setUserId(session.getUserId());
        } catch (Exception e) {
            log.error("id错误",e);
        }
        setUserRoleTypes(session.getUserRoleTypes());
        setRealName(session.getRealName());
        setDepartmentId(session.getDepartmentId());
        setDepartmentCode(session.getDepartmentCode());
        setDepartmentName(session.getDepartmentName());
        setAreaId(session.getAreaId());
        setParentAreaId(session.getParentAreaId());
        setAreaId(session.getAreaId());
        setParentAreaId(session.getParentDepartmentId());
    }

    /**
     * 当前用户Id
     */
    private String userId;

    /**
     * 用户类型
     */
    private List<UserRoleType> userRoleTypes;

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

    @Override
    public String getUserId() throws Exception {
        return null;
    }


}