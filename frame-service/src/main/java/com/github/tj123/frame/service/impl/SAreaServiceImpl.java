package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SAreaPo;
import com.github.tj123.frame.api.service.SAreaService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service
public class SAreaServiceImpl implements SAreaService {

    @Autowired
    private SAreaMapper mapper;

    @Override
    public void add(SAreaPo po) throws Exception {
        mapper.insert(po);
    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void edit(SAreaPo po) throws Exception {
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

}
