package com.github.tj123.frame.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;
import com.github.tj123.frame.api.po.sys.RoleFuncPo;
import com.github.tj123.frame.api.po.sys.RolePo;
import com.github.tj123.frame.api.service.RoleService;
import com.github.tj123.frame.service.common.PageUtils;
import com.github.tj123.frame.service.common.UuidUtil;
import com.github.tj123.frame.service.mapper.RoleFuncMapper;
import com.github.tj123.frame.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 2017/9/19.
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleMapper mapper;

    @Autowired
    RoleFuncMapper funcMapper;


    @Override
    public PageResponse<Map<String, Object>> list(PageRequest request) throws Exception {
        return PageUtils.query(request, () -> mapper.list());
    }

    @Override
    @Transactional
    public void add(RolePo po, List<String> roles) throws Exception {
        String uuid = UuidUtil.getUUID();
        po.setId(uuid);
        List<RoleFuncPo> list = new ArrayList<>();
        if(roles != null && !roles.isEmpty()){
            for (String role : roles) {
                RoleFuncPo funcPo = new RoleFuncPo();
                funcPo.setId(UuidUtil.getUUID());
                funcPo.setRoleId(uuid);
                funcPo.setFuncId(role);
                list.add(funcPo);
            }
        }
        mapper.insert(po);
        if(!list.isEmpty()){
            //funcMapper.insertList(list);
            for (RoleFuncPo funcPo : list) {
                funcMapper.insert(funcPo);
            }
        }

    }

    @Override
    public List<Map<String, Object>> allRoles() throws Exception {
        return mapper.list();
    }


}
