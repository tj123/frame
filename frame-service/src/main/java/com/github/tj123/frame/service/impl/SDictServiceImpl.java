package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.pojo.po.SDictPo;
import com.github.tj123.frame.api.service.SDictService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SDictMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Random;

@Service
public class SDictServiceImpl implements SDictService {

    @Autowired
    private SDictMapper mapper;

    @Override
    public void add(SDictPo po) throws Exception {
        po.setOrder_(new Random().nextInt(20));
        mapper.insert(po);
    }

    @Override
    public void del(String id) throws Exception {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void edit(SDictPo po) throws Exception {
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
