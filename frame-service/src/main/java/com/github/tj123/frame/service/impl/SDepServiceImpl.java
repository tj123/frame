package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SDepPo;
import com.github.tj123.frame.api.service.SDepService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SDepMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service
public class SDepServiceImpl implements SDepService {

    @Autowired
    private SDepMapper mapper;

    @Override
    public void add(SDepPo po) throws Exception {
        mapper.insert(po);
    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void edit(SDepPo po) throws Exception {
        mapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public SDepPo get(String id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list(request));
    }

}
