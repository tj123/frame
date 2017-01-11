package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.common.GlobalSession;
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
import com.shundian.lib.util.PasswordUtil;
import com.shundian.lib.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public Map<String, Object> login(String username, String password, GlobalSession session) throws Exception {
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
        return map;
    }

    @Override
    public PageResult<Map<String, Object>> list(Page page) throws Exception {
        PageUtil<Map<String, Object>> pageUtil = new PageUtil<>();
        pageUtil.startPage(page);
        return pageUtil.assembleResult(mapper.list(page.assembleSearch()));
    }


    public void updateSession(String userId, GlobalSession session) throws Exception {
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
