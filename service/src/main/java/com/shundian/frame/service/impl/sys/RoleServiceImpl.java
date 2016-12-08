package com.shundian.frame.service.impl.sys;


import com.shundian.frame.api.po.sys.RolePo;
import com.shundian.frame.api.service.sys.RoleService;
import com.shundian.frame.common.PageUtil;
import com.shundian.frame.mapper.sys.RoleMapper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public PageResult<RolePo> list(Page page) throws Exception {
        PageUtil<RolePo> util = new PageUtil<>();
        util.startPage(page);
        return util.assembleResult(mapper.selectAll());
    }

    @Override
    public RolePo list(String id) throws Exception {
        return null;
    }

    @Override
    public void add(RolePo rolePo) throws Exception {
        mapper.insert(rolePo);
    }

}
