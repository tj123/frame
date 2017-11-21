package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleMapper mapper;


    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list());
    }


}
