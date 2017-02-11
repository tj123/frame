package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.common.FakeGlobalSession;
import com.shundian.frame.api.entity.sys.AuthFunction;
import com.shundian.frame.api.entity.sys.AuthFunctionModule;
import com.shundian.frame.api.po.sys.DepartmentPo;
import com.shundian.frame.api.po.sys.UserPo;
import com.shundian.frame.api.service.sys.UserService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.mapper.sys.DepartmentMapper;
import com.shundian.frame.mapper.sys.UserMapper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import com.shundian.lib.authorize.Authorization;
import com.shundian.lib.common.bean.validate.impl.NotValidException;
import com.shundian.lib.function.DefaultModule;
import com.shundian.lib.util.PasswordUtil;
import com.shundian.lib.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    private static final String FUNCTION_TRIM = "com.shundian.frame.api.common.function.";
    private static final String MODULE_TRIM = "com.shundian.frame.api.common.function.module.";

    @Override
    public Map<String, Object> login(String username, String password) throws Exception {
        FakeGlobalSession session = new FakeGlobalSession();
        Map<String, Object> userIdPassword = mapper.selectUserId(username);
        if (StringUtil.isBlank(userIdPassword))
            throw new NotValidException("username", 1, "用户名或密码错误！");
        String userId = (String) userIdPassword.get("id");
        String dbPassword = (String) userIdPassword.get("password");
        if (StringUtil.isBlank(userId))
            throw new NotValidException("username", 1, "用户名或密码错误！");
        Map<String, Object> map = new HashMap<>();
        if (!PasswordUtil.isMatch(password, dbPassword))
            throw new NotValidException("username", 1, "用户名或密码错误！");
        updateSession(userId, session);
        map.put("name", session.getRealName());
        Map<String,Object> rtn = new HashMap<String,Object>();
        rtn.put("response",map);
        rtn.put("_fakeSession",session);
        return rtn;
    }

    @Override
    public PageResult<Map<String, Object>> list(Page page) throws Exception {
        PageUtil<Map<String, Object>> pageUtil = new PageUtil<>();
        pageUtil.startPage(page);
        return pageUtil.assembleResult(mapper.list(page.assembleSearch()));
    }

    @Override
    public List<AuthFunction> getAuths(String userId) throws Exception {
        List<AuthFunction> userAuths = mapper.getUserAuths(userId);
        for (AuthFunction userAuth : userAuths) {
            String key = userAuth.getKey();
            if(key != null){
                userAuth.setKey(key.replace(FUNCTION_TRIM,"").replaceAll("Function$","").trim());
            }
            for (AuthFunctionModule authFunctionModule : userAuth.getModules()) {
                String moduleKey = authFunctionModule.getKey();
                if(moduleKey != null){
                    if(DefaultModule.class.getTypeName().equals(moduleKey)){
                        authFunctionModule.setKey("Default");
                    }else{
                        authFunctionModule.setKey(moduleKey.replace(MODULE_TRIM,"").replaceAll("Module$","").trim());
                    }
                }
            }
        }
        return userAuths;
    }


    public void updateSession(String userId, FakeGlobalSession session) throws Exception {
        UserPo po = mapper.selectByPrimaryKey(userId);
        session.setAuthorization(new Authorization(mapper.getFunctions(userId)));
        session.setRealName(po.getRealName());
        session.setDepartmentId(po.getDepartmentId());
        session.setUserId(userId);
        System.out.println(session);
        String departmentId = session.getDepartmentId();
        if (StringUtil.isNotBlank(departmentId)) {
            DepartmentPo departmentPo = departmentMapper.selectByPrimaryKey(departmentId);
            if (departmentPo != null) {
                session.setDepartmentCode(departmentPo.getCode());
            }
        }
    }
}
