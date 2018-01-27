package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.exception.MessageException;
import com.github.tj123.frame.api.common.utils.AreaUtils;
import com.github.tj123.frame.api.envm.AreaType;
import com.github.tj123.frame.api.pojo.po.SAreaPo;
import com.github.tj123.frame.api.service.SAreaService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.mapper.SAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Random;

@Service
public class SAreaServiceImpl implements SAreaService {

    @Autowired
    private SAreaMapper mapper;

    @Override
    public void add(SAreaPo po) throws Exception {
        AreaType areaType = AreaUtils.areaType(po.getAreaId());
        po.setLevel_(areaType.getLevel());
        po.setIsCapital(0);
        po.setIsGov(0);
        po.setOrder_(new Random().nextInt(10));
        if(!AreaUtils.isParent(po.getParentId(),po.getAreaId())){
            throw new MessageException(String.format("%s 不是 %s 的上级区域!",po.getParentId(),po.getAreaId()));
        }
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
