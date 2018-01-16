package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SUserPo;
import com.github.tj123.frame.api.service.SUserService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
    public SUserPo get(String id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list(request));
    }

}
