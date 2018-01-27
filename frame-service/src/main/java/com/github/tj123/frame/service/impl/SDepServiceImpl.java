package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.common.utils.AreaUtils;
import com.github.tj123.frame.api.pojo.po.SDepPo;
import com.github.tj123.frame.api.pojo.po.SDepRolePo;
import com.github.tj123.frame.api.service.SDepService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.common.UuidUtil;
import com.github.tj123.frame.service.mapper.SDepMapper;
import com.github.tj123.frame.service.mapper.SDepRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SDepServiceImpl implements SDepService {

    @Autowired
    private SDepMapper mapper;

    @Autowired
    SDepRoleMapper depRoleMapper;

    @Override
    @Transactional
    public void add(SDepPo po, List<String> roles) throws Exception {
        String id = UuidUtil.getUUID();
        po.setId(id);
        mapper.insert(po);
        for (String role : roles) {
            SDepRolePo rs = new SDepRolePo();
            rs.setDepId(id);
            rs.setRoleId(role);
            depRoleMapper.insert(rs);
        }
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
        request.put("areaId", AreaUtils.simple((String)request.get("areaId")));
        return PageUtils.query(request, () -> mapper.list(request));
    }

}
