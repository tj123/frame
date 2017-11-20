package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.exception.MessageException;
import com.github.tj123.frame.api.po.UserPo;
import com.github.tj123.frame.api.service.UserService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.common.PasswordUtil;
import com.github.tj123.frame.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Service
public class UserServiceImpl implements UserService {


    public Map<String, Object> test() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("aaa", "haha");
        return map;
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
    public PageResponse<UserPo> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.selectAll());
    }

    @Autowired
    UserMapper mapper;

//    @Override
//    public Map<String, Object> list(Integer page, Integer size) throws Exception {
//
//        if(page == null){
//            page = 1;
//        }
//        if(size == null){
//            size = 10;
//        }
//
//        Map<String, Object> map = new HashMap<>();
//
//
//        Page<Object> startPage = PageHelper.startPage(page, size);
//        List<UserPo> list = mapper.selectAll();
//
//        map.put("rows", list);
//
//        map.put("total", startPage.getTotal());
//        return map;
//    }

    @Override
    public Map<String, Object> auth(String userId) throws Exception {
        return null;
    }

    @Override
    public void add(UserPo userPo) throws Exception {
        mapper.insert(userPo);
    }

    @Override
    public void update(UserPo userPo) throws Exception {
        mapper.updateByPrimaryKeySelective(userPo);
    }

    @Override
    public Map<String, Object> getInfo(String userId) throws Exception {
        UserPo userPo = mapper.selectByPrimaryKey(userId);
        if(userPo != null){
            return userPo.toMap();
        }
        return new HashMap<>();
    }

}
