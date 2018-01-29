package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.exception.MessageException;
import com.github.tj123.frame.api.pojo.po.SUserPo;
import com.github.tj123.frame.api.service.SUserService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.common.PasswordUtil;
import com.github.tj123.frame.service.mapper.SUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class SUserServiceImpl implements SUserService {

    @Autowired
    private SUserMapper mapper;

    @Override
    public void add(SUserPo po) throws Exception {
        mapper.insert(po);
    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void edit(SUserPo po) throws Exception {
        mapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public Map<String, Object> get2(String id) throws Exception {
        return mapper.get(id);
    }

    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list(request));
    }


    @Override
    public String login(String userName, String password) throws Exception {
        if (userName == null || userName.trim().equals(""))
            throw new MessageException("请输入用户名");
        if (password == null || password.trim().equals(""))
            throw new MessageException("请输入密码");
        Map<String, Object> map = mapper.selectUser(userName);
        if(map == null)
            throw new MessageException("用户名或密码错误");
        String userId = (String) map.get("id");
        String dbPassword = (String) map.get("password");
        if (dbPassword == null || dbPassword.trim().equals(""))
            throw new MessageException("用户名或密码错误");
        if (!PasswordUtil.isMatch(password, dbPassword))
            throw new MessageException("用户名或密码错误");
        return userId;
    }

    @Override
    public List<Map<String, Object>> auth(String userId) throws Exception {
        return mapper.auth(userId);
    }

}
