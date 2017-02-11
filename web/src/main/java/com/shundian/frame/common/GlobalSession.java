package com.shundian.frame.common;

import com.shundian.frame.api.common.FakeGlobalSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by TJ on 2017/2/11.
 */
@Component
@SessionScope
public class GlobalSession extends FakeGlobalSession {
  
  public void copy(FakeGlobalSession fakeGlobalSession){
    setAreaId(fakeGlobalSession.getAreaId());
    setParentAreaId(fakeGlobalSession.getParentAreaId());
    setAreaLevel(fakeGlobalSession.getAreaLevel());
    setAuthorization(fakeGlobalSession.getAuthorization());
    setDepartmentCode(fakeGlobalSession.getDepartmentCode());
    setDepartmentId(fakeGlobalSession.getDepartmentId());
    setDepartmentName(fakeGlobalSession.getDepartmentName());
    setParentDepartmentId(fakeGlobalSession.getParentDepartmentId());
    setRealName(fakeGlobalSession.getRealName());
    setUserId(fakeGlobalSession.getUserId());
    setUserRoleTypes(fakeGlobalSession.getUserRoleTypes());
  }
  
}
