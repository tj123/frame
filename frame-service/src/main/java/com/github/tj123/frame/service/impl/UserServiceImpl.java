package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.tj123.frame.api.po.UserPo;
import com.github.tj123.frame.api.service.UserService;
import com.github.tj123.frame.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    UserMapper mapper;

    @Override
    public Map<String, Object> list(Integer page, Integer size) throws Exception {

        if(page == null){
            page = 1;
        }
        if(size == null){
            size = 10;
        }

        Map<String, Object> map = new HashMap<>();
//
//        List<Map<String, Object>> list = new ArrayList<>();
//
//        for (int i = 0; i < 8; i++) {
//            Map<String, Object> mp = new HashMap<>();
//            mp.put("name", "sdfd" + i);
//            mp.put("id", i);
//            mp.put("age", Math.random());
//            list.add(mp);
//        }

        Page<Object> startPage = PageHelper.startPage(page, size);
        List<UserPo> list = mapper.selectAll();

        map.put("rows", list);

        map.put("total", startPage.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> auth(String userId) throws Exception {
        return null;
    }
}
