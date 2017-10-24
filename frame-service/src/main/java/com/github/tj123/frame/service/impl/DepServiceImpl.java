package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.service.DepService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.DepMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Service
public class DepServiceImpl implements DepService {


    @Override
    public PageResponse<Map<String,Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list());
    }

    @Autowired
    DepMapper mapper;

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



//    @Override
//    public void add(UserPo userPo) throws Exception {
//        mapper.insert(userPo);
//    }
//
//    @Override
//    public void update(UserPo userPo) throws Exception {
//        mapper.updateByPrimaryKeySelective(userPo);
//    }

}
